package com.example.composepokedex.data.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.composepokedex.data.local.PokedexDatabase
import com.example.composepokedex.data.local.entity.PokemonEntity
import com.example.composepokedex.data.mapper.toPokemon
import com.example.composepokedex.data.mapper.toPokemonEntity
import com.example.composepokedex.data.mapper.toPokemonList
import com.example.composepokedex.data.remote.PokedexApi
import com.example.composepokedex.domain.model.Pokemon
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import kotlin.math.floor

@OptIn(ExperimentalPagingApi::class)
class PokedexRemoteMediator @Inject constructor(
    val pokedexApi: PokedexApi,
    val pokedexDatabase: PokedexDatabase
): RemoteMediator<Int, PokemonEntity>() {
    private val pokemonDao = pokedexDatabase.pokemonDao

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {
        return try {
            val currentPage:Int = when(loadType){
                LoadType.REFRESH -> {
                    val lastPokemon = pokemonDao.getLastPokemon()
                    if(lastPokemon == null){
                        0
                    } else {
                        -1
                    }
                }
                LoadType.PREPEND -> {
                    val firstItem = state.firstItemOrNull()
                    if(firstItem == null){
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }

                    val page = floor(firstItem.number / 20.0)
                    if(page == 0.0){
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }

                    page.toInt()
                }
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if(lastItem == null){
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }

                    val pokemonEntity = pokemonDao.find(lastItem.id!!)
                    Log.d("APP", "lastItem.id is ${lastItem.id}, and pkm entity is ${pokemonEntity}")
                    if(pokemonEntity == null){
                        floor(lastItem.number / 20.0).toInt()
                    } else {
                        floor(pokemonEntity.number / 20.0).toInt()
                    }
                }
            }

            if(currentPage == -1){
                Log.d("APP", "current page is $currentPage")
                return MediatorResult.Success(endOfPaginationReached = false)
            }

            val nextPage = if(currentPage == 0) 0 else { currentPage * 20 }
            Log.d("APP", "Current: $currentPage, Next: $nextPage")

            val response = pokedexApi.fetchPokemons(
                offset = nextPage,
                limit = 20
            )

            val endOfPaginationReached = response.next.isNullOrEmpty()
//            val previousPage = if(currentPage == 0) null else currentPage - 1
//            val nextPage = if(endOfPaginationReached) null else currentPage + 1

            val pokemonList = mutableListOf<PokemonEntity>()
            response.results?.forEach {
                val pokemonResponseDto = pokedexApi.fetchPokemon(it.name!!)
                pokemonList.add(pokemonResponseDto.toPokemonEntity())
            }

            pokedexDatabase.withTransaction {
//                if(loadType == LoadType.REFRESH){
//                    Log.d("APP", "DESTROY ALL POKEMON IN DB!!!!")
//                    pokemonDao.destroyAll()
//                }

                Log.d("APP", "inserting pokemon, loadtype is ${loadType}")
                Log.d("APP", "numbers of inserts are ${pokemonList.map { it.number }}")
                pokemonDao.insertAllPokemon(pokemonList)
            }

            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}
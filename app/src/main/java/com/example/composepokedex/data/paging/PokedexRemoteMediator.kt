package com.example.composepokedex.data.paging

import android.icu.util.TimeUnit
import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.composepokedex.data.local.PokedexDatabase
import com.example.composepokedex.data.local.entity.PokemonEntity
import com.example.composepokedex.data.mapper.toPokemonEntity
import com.example.composepokedex.data.remote.PokedexApi
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

    override suspend fun initialize(): InitializeAction {
        val pokemonEntity = pokemonDao.getLastPokemon()
        return if(pokemonEntity != null) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {
        return try {
            val currentPage:Int = when(loadType){
                LoadType.REFRESH -> {
                    0
                }
                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.APPEND -> {
                    val lastPokemonEntity = pokedexDatabase.withTransaction {
                        pokemonDao.getLastPokemon()
                    }

                    if (lastPokemonEntity == null) {
                        0
                    } else {
                        floor(lastPokemonEntity.number / 20.0).toInt()
                    }
                }
            }

            val nextPage = if(currentPage == 0) 0 else { currentPage * 20 }
            Log.d("APP", "Current: $currentPage, Next: $nextPage")

            val response = pokedexApi.fetchPokemons(
                offset = nextPage,
                limit = 20
            )

            val pokemonList = mutableListOf<PokemonEntity>()
            response.results?.forEach {
                val pokemonResponseDto = pokedexApi.fetchPokemon(it.name!!)
                pokemonList.add(pokemonResponseDto.toPokemonEntity())
            }

            pokedexDatabase.withTransaction {
                if(loadType == LoadType.REFRESH){
                    Log.d("APP", "DESTROY ALL POKEMON IN DB!!!!")
                    pokemonDao.destroyAll()
                }

                Log.d("APP", "inserting pokemon, loadtype is ${loadType}")
                Log.d("APP", "numbers of inserts are ${pokemonList.map { it.number }}")
                pokemonDao.insertAllPokemon(pokemonList)
            }

            val endOfPaginationReached = response.next.isNullOrEmpty()
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}
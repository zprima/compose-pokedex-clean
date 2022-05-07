package com.example.composepokedex.data.repository

import androidx.paging.*
import com.example.composepokedex.data.local.PokedexDatabase
import com.example.composepokedex.data.mapper.toPokemon
import com.example.composepokedex.data.paging.PokedexRemoteMediator
import com.example.composepokedex.data.remote.PokedexApi
import com.example.composepokedex.domain.model.Pokemon
import com.example.composepokedex.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokedexApi: PokedexApi,
    private val pokedexDatabase: PokedexDatabase
): PokemonRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun fetchAllPokemons(): Flow<PagingData<Pokemon>> {
        val pokemonDao = pokedexDatabase.pokemonDao
        val pagingSourceFactory = { pokemonDao.fetchPokemon() }

        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = PokedexRemoteMediator(
                pokedexApi = pokedexApi,
                pokedexDatabase = pokedexDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { pagingData ->
              pagingData.map { pokemonEntity -> pokemonEntity.toPokemon() }
        }
    }
}
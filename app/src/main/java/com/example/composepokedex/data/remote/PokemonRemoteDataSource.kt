package com.example.composepokedex.data.remote

import com.example.composepokedex.data.mapper.toPokemonList
import com.example.composepokedex.domain.model.Pokemon
import com.example.composepokedex.domain.model.PokemonList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class PokemonRemoteDataSource(
    private val pokedexApi: PokedexApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun fetchPokemons(): List<PokemonList> =
        withContext(ioDispatcher) {
            pokedexApi.fetchPokemons().results.map {
                it.toPokemonList()
            }
        }
}
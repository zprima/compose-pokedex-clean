package com.example.composepokedex.data.local

import com.example.composepokedex.data.mapper.toPokemon
import com.example.composepokedex.data.mapper.toPokemonEntity
import com.example.composepokedex.data.mapper.toPokemonList
import com.example.composepokedex.domain.model.Pokemon
import com.example.composepokedex.domain.model.PokemonList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonLocalDataSource(
    private val db: PokedexDatabase,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
//    suspend fun fetchPokemons(page:Int): List<Pokemon> =
//        withContext(ioDispatcher) {
//            db.pokemonDao.fetchPokemon(page).map {
//                it.toPokemon()
//            }
//        }
//
//    suspend fun insert(pokemon: Pokemon){
//        withContext(ioDispatcher){
//            db.pokemonDao.insertPokemon(pokemon.toPokemonEntity())
//        }
//    }
}
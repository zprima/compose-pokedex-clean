package com.example.composepokedex.domain.repository

import com.example.composepokedex.domain.model.PokemonList
import com.example.composepokedex.util.Resource
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun fetchPokemons(page: Int): Flow<Resource<List<PokemonList>>>
}
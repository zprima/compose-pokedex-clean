package com.example.composepokedex.domain.repository

import androidx.paging.PagingData
import com.example.composepokedex.data.local.entity.PokemonEntity
import com.example.composepokedex.domain.model.Pokemon
import com.example.composepokedex.domain.model.PokemonList
import com.example.composepokedex.util.Resource
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun fetchAllPokemons(): Flow<PagingData<Pokemon>>
}
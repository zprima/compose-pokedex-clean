package com.example.composepokedex.ui.screen.pokemonList

import androidx.paging.PagingData
import com.example.composepokedex.domain.model.Pokemon
import com.example.composepokedex.domain.model.PokemonList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

data class PokemonListUiState(
    val pokemonList: Flow<PagingData<Pokemon>> = flowOf(PagingData.empty()),
    val page: Int = 0,
    val isLoading:Boolean = false
)
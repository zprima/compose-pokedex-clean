package com.example.composepokedex.ui.screen.pokemonList

import com.example.composepokedex.domain.model.Pokemon
import com.example.composepokedex.domain.model.PokemonList

data class PokemonListUiState(
    val pokemonList: List<PokemonList> = listOf(),
    val page: Int = 0,
    val isLoading:Boolean = false
)
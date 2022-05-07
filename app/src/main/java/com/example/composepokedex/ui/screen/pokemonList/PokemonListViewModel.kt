package com.example.composepokedex.ui.screen.pokemonList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.composepokedex.domain.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    pokemonRepository: PokemonRepository
): ViewModel() {
    var uiState: PokemonListUiState by mutableStateOf(
        PokemonListUiState(
            pokemonList = pokemonRepository.fetchAllPokemons().cachedIn(viewModelScope)
        )
    )
        private set
}
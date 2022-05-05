package com.example.composepokedex.ui.screen.pokemonList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.map
import com.example.composepokedex.domain.model.PokemonList
import com.example.composepokedex.domain.repository.PokemonRepository
import com.example.composepokedex.domain.useCase.FetchPokemonListUseCase
import com.example.composepokedex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
): ViewModel() {
    var uiState: PokemonListUiState by mutableStateOf(PokemonListUiState())
        private set

    val getPokemon = pokemonRepository.fetchAllPokemons()

//    init {
//        viewModelScope.launch {
//            pokemonRepository.fetchAllPokemons().collect {
//                uiState = uiState.copy(pokemonList = listOf(it.map { it -> it }))
//            }
//        }
//    }
}
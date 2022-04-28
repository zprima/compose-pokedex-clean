package com.example.composepokedex.ui.screen.pokemonList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composepokedex.domain.model.PokemonList
import com.example.composepokedex.domain.useCase.FetchPokemonListUseCase
import com.example.composepokedex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val fetchPokemonListUseCase: FetchPokemonListUseCase
): ViewModel() {
    var uiState: PokemonListUiState by mutableStateOf(PokemonListUiState())
        private set

    init {
        viewModelScope.launch {
            fetchPokemonListUseCase.invoke().collectLatest { result ->
                when(result){
                    is Resource.Success -> {
                        result.data?.let {
                            uiState = uiState.copy(
                                isLoading = false,
                                pokemonList = it
                            )
                        }
                    }
                    is Resource.Error -> {
                        uiState = uiState.copy(
                            isLoading = false,

                        )
                    }
                    is Resource.Loading -> {
                        uiState = uiState.copy(
                            isLoading = true
                        )
                    }
                }
            }
        }
    }
}
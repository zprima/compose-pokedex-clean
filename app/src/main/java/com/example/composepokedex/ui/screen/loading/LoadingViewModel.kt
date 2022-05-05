package com.example.composepokedex.ui.screen.loading

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.composepokedex.domain.repository.PokemonRepository
import com.example.composepokedex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoadingViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {
    var loaded by mutableStateOf(false)
        private set

    init {
        getPokemon()
    }

    fun getPokemon(){
        viewModelScope.launch {
            pokemonRepository.fetchAllPokemons().collect {
                loaded = true
                return@collect
            }
        }
    }
}
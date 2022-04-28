package com.example.composepokedex.ui.screen.pokemonList

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState

    Column(){
        uiState.pokemonList.forEach {
            Text(it.name)
        }
    }

}
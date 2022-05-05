package com.example.composepokedex.ui.screen.pokemonList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items

@Composable
fun PokemonListScreen(
    navController: NavController,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
//    val uiState = viewModel.uiState
    val lazyItems = viewModel.getPokemon.collectAsLazyPagingItems()

    LazyColumn(){
        items(lazyItems) {
            if (it != null) {
                Text(it.name)
                Text(it.officialArtWorkUrl)
                Text(it.types.joinToString(", "))
                Spacer(Modifier.height(8.dp))
            }
        }
    }

}
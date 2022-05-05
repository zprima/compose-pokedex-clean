package com.example.composepokedex.ui.navigation

sealed class Screen(val route:String){
    object Loading: Screen("loading")
    object PokemonList: Screen("pokemon_list")
}

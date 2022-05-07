package com.example.composepokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composepokedex.ui.navigation.Screen
import com.example.composepokedex.ui.screen.pokemonList.PokemonListScreen
import com.example.composepokedex.ui.theme.ComposePokedexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            ComposePokedexTheme {
                NavHost(navController = navController, startDestination = Screen.PokemonList.route) {
                    composable(Screen.PokemonList.route) { PokemonListScreen(navController) }
                }
            }
        }
    }
}


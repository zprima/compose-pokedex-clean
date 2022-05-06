package com.example.composepokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composepokedex.ui.navigation.Screen
import com.example.composepokedex.ui.screen.loading.LoadingScreen
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
                    composable(Screen.Loading.route) { LoadingScreen(navController) }
                    composable(Screen.PokemonList.route) { PokemonListScreen(navController) }
                }
            }
        }
    }
}


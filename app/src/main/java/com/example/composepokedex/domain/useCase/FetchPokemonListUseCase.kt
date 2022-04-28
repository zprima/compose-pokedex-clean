package com.example.composepokedex.domain.useCase

import com.example.composepokedex.domain.model.PokemonList
import com.example.composepokedex.domain.repository.PokemonRepository
import com.example.composepokedex.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext

class FetchPokemonListUseCase(
    private val pokemonRepository: PokemonRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {
    suspend operator fun invoke() = withContext(defaultDispatcher) {
        pokemonRepository.fetchPokemons(0)
    }
}
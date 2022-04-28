package com.example.composepokedex.data.repository

import com.example.composepokedex.data.remote.PokemonRemoteDataSource
import com.example.composepokedex.domain.model.PokemonList
import com.example.composepokedex.domain.repository.PokemonRepository
import com.example.composepokedex.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource
): PokemonRepository {
    override suspend fun fetchPokemons(page: Int): Flow<Resource<List<PokemonList>>> {
        return flow{
            emit(Resource.Loading(true))

            try {
                val data = pokemonRemoteDataSource.fetchPokemons()
                emit(Resource.Success(data))
            }
            catch (e: HttpException){
                emit(Resource.Error("Failed to get data"))
                return@flow
            }
        }
    }
}
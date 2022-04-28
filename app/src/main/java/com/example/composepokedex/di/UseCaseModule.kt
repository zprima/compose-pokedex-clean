package com.example.composepokedex.di

import com.example.composepokedex.data.remote.PokedexApi
import com.example.composepokedex.domain.repository.PokemonRepository
import com.example.composepokedex.domain.useCase.FetchPokemonListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun providerFetchPokemonListUseCase(
        pokemonRepository: PokemonRepository
    ): FetchPokemonListUseCase {
        return FetchPokemonListUseCase(pokemonRepository)
    }
}
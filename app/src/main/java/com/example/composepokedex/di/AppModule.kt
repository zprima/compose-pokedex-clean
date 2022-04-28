package com.example.composepokedex.di

import com.example.composepokedex.data.remote.PokedexApi
import com.example.composepokedex.data.remote.PokemonRemoteDataSource
import com.example.composepokedex.data.repository.PokemonRepositoryImpl
import com.example.composepokedex.domain.repository.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providerPokedexApi(): PokedexApi {
        return Retrofit.Builder()
            .baseUrl(PokedexApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC })
                    .build()
            )
            .build()
            .create(PokedexApi::class.java)
    }

    @Provides
    @Singleton
    fun providePokemonRemoteDataSource(pokedexApi: PokedexApi): PokemonRemoteDataSource{
        return PokemonRemoteDataSource(pokedexApi)
    }
}
package com.example.composepokedex.data.remote

import com.example.composepokedex.data.remote.dto.PokedexListResponseDto
import com.example.composepokedex.data.remote.dto.PokedexPokemonResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokedexApi {
    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
    }

    @GET("pokemon")
    suspend fun fetchPokemons(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 20
    ): PokedexListResponseDto

    @GET("pokemon/{id}")
    suspend fun fetchPokemon(@Path("id") id: Int): PokedexPokemonResponseDto

    @GET("pokemon/{name}")
    suspend fun fetchPokemon(@Path("name") name: String): PokedexPokemonResponseDto
}
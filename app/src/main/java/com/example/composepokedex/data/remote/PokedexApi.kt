package com.example.composepokedex.data.remote

import com.example.composepokedex.data.remote.dto.PokemonListDto
import com.example.composepokedex.data.remote.dto.PokemonListResponseDto
import okhttp3.Response
import retrofit2.http.GET

interface PokedexApi {
    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
    }

    @GET("pokemon")
    suspend fun fetchPokemons(): PokemonListResponseDto

}
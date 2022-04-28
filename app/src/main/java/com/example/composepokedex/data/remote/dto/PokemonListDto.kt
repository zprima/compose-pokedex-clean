package com.example.composepokedex.data.remote.dto

import com.squareup.moshi.Json

data class PokemonListDto(
    @field:Json(name = "name") val name:String,
    @field:Json(name = "url") val url:String
)
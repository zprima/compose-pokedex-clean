package com.example.composepokedex.data.remote.dto

import com.squareup.moshi.Json

data class PokemonListResponseDto(
    @field:Json(name = "count")
    val count:Int,

    @field:Json(name = "results")
    val results:List<PokemonListDto>
)
package com.example.composepokedex.data.mapper

import com.example.composepokedex.data.remote.dto.PokemonListDto
import com.example.composepokedex.domain.model.PokemonList

fun PokemonListDto.toPokemonList(): PokemonList {
    return PokemonList(
        name = name,
        url = url
    )
}
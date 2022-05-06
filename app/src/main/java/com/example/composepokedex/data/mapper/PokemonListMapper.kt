package com.example.composepokedex.data.mapper

import androidx.room.ColumnInfo
import com.example.composepokedex.data.local.entity.PokemonEntity
import com.example.composepokedex.data.remote.dto.PokedexListResultDto
import com.example.composepokedex.data.remote.dto.PokedexPokemonResponseDto
import com.example.composepokedex.domain.model.Pokemon
import com.example.composepokedex.domain.model.PokemonList

fun PokedexListResultDto.toPokemonList(): PokemonList {
    return PokemonList(
        name = name!!,
        url = url!!
    )
}

fun PokemonEntity.toPokemon(): Pokemon {
    return Pokemon(
        id = id!!,
        number = number,
        name = name,
        officialArtWorkUrl = imageUrl,
        types = types.split(",")
    )
}

fun PokedexPokemonResponseDto.toPokemon(): Pokemon{
    return Pokemon(
        id = id!!,
        number = id,
        name = name!!,
        officialArtWorkUrl = sprites?.other?.officialArtwork?.frontDefault!!,
        types = types!!.map { it.type?.name!! }
    )
}

fun PokedexPokemonResponseDto.toPokemonEntity(): PokemonEntity{
    return PokemonEntity(
        number = id!!,
        name = name!!,
        imageUrl = sprites?.other?.officialArtwork?.frontDefault!!,
        types = types!!.joinToString(",") { it.type?.name!! }
    )
}

fun Pokemon.toPokemonEntity(): PokemonEntity{
    return PokemonEntity(
        id = id,
        number = number,
        name = name,
        imageUrl = officialArtWorkUrl,
        types = types.joinToString(",")
    )
}
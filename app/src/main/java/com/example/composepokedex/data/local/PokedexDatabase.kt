package com.example.composepokedex.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.composepokedex.data.local.dao.PokemonDao
import com.example.composepokedex.data.local.entity.PokemonEntity

@Database(
    entities = [PokemonEntity::class],
    version = 1
)
abstract class PokedexDatabase(): RoomDatabase() {
    abstract val pokemonDao: PokemonDao
}
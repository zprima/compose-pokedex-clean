package com.example.composepokedex.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.example.composepokedex.data.local.entity.PokemonEntity

@Dao
interface PokemonDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(
        pokemonEntity: PokemonEntity
    )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPokemon(
        pokemonList: List<PokemonEntity>
    )

//    @Query("SELECT * from pokemonentity LIMIT 20 OFFSET :offset * 20")
//    suspend fun fetchPokemon(offset: Int): List<PokemonEntity>

    @Query("SELECT * from pokemonentity")
    fun fetchPokemon(): PagingSource<Int, PokemonEntity>

    @Query("DELETE FROM pokemonentity")
    suspend fun destroyAll()

    @Query("SELECT * from pokemonentity ORDER BY id DESC LIMIT 1")
    suspend fun getLastPokemon(): PokemonEntity?

    @Query("SELECT * FROM pokemonentity WHERE id = :id")
    suspend fun find(id: Int): PokemonEntity?
}
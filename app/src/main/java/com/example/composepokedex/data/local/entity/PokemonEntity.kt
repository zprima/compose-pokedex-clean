package com.example.composepokedex.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,

    @ColumnInfo val number:Int,
    @ColumnInfo val name:String,
    @ColumnInfo val imageUrl:String,
    @ColumnInfo val types:String
)
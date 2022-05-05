package com.example.composepokedex.domain.model

data class Pokemon(
    val id:Int,
    val number:Int,
    val name:String,
    val officialArtWorkUrl:String,
    val types: List<String>
)

package com.example.composepokedex.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TypeXX(
    @SerialName("slot")
    val slot: Int?,
    @SerialName("type")
    val type: TypeXXX?
)
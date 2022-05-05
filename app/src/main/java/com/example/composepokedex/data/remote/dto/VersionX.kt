package com.example.composepokedex.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VersionX(
    @SerialName("name")
    val name: String?,
    @SerialName("url")
    val url: String?
)
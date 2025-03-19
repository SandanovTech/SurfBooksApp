package com.example.surfbooksapp.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookDTO(
    @SerialName("q") val q : String,
)

package com.example.surfbooksapp.data.local.model

import androidx.room.Entity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity
data class BookEntity(
    val q : String,
)

package com.example.surfbooksapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey val id: String,
    val volumeInfoLocal: VolumeInfoLocal? = null,
    val isFavourite: Boolean = false,
)

data class VolumeInfoLocal(
    val pageCount: Int? = null,
    val printType: String? = null,
    val previewLink: String? = null,
    val canonicalVolumeLink: String? = null,
    val description: String? = null,
    val language: String? = null,
    val title: String? = null,
    val imageLinksLocal: ImageLinksLocal? = null,
    val publisher: String? = null,
    val publishedDate: String? = null,
    val categories: List<String?>? = null,
    val maturityRating: String? = null,
    val allowAnonLogging: Boolean? = null,
    val contentVersion: String? = null,
    val authors: List<String?>? = null,
    val infoLink: String? = null,
    val subtitle: String? = null
)

data class ImageLinksLocal(
    val thumbnail: String? = null,
    val smallThumbnail: String? = null
)
package com.example.surfbooksapp.domain.model


data class Book(
    val id: String,
    val volumeInfo: VolumeInfo? = null,
    var isFavourite : Boolean = false
)

data class VolumeInfo(
    val pageCount: Int? = null,
    val printType: String? = null,
    val previewLink: String? = null,
    val canonicalVolumeLink: String? = null,
    val description: String? = null,
    val language: String? = null,
    val title: String? = null,
    val imageLinks: ImageLinks? = null,
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

data class ImageLinks(
    val thumbnail: String? = null,
    val smallThumbnail: String? = null
)

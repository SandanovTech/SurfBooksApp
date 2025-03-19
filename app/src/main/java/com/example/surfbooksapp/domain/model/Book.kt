package com.example.surfbooksapp.domain.model


data class Book(
    val totalItems: Int? = null,
    val kind: String? = null,
    val items: List<DomainItemsItem?>? = null
)

data class DomainItemsItem(
    val kind: String? = null,
    val domainVolumeInfo: DomainVolumeInfo? = null,
    val etag: String? = null,
    val id: String? = null,
    val selfLink: String? = null
)

data class DomainVolumeInfo(
    val pageCount: Int? = null,
    val printType: String? = null,
    val previewLink: String? = null,
    val canonicalVolumeLink: String? = null,
    val description: String? = null,
    val language: String? = null,
    val title: String? = null,
    val imageLinks: DomainImageLinks? = null,
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

data class DomainImageLinks(
    val thumbnail: String? = null,
    val smallThumbnail: String? = null
)

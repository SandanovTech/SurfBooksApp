package com.example.surfbooksapp.data.network.model

import kotlinx.serialization.SerialName

data class BookResponse(
	@SerialName("totalItems")
	val totalItems: Int? = null,

	@SerialName("kind")
	val kind: String? = null,

	@SerialName("items")
	val items: List<ItemsItem?>? = null
)

data class ItemsItem(
	@SerialName("kind")
	val kind: String? = null,

	@SerialName("volumeInfo")
	val volumeInfo: VolumeInfo? = null,

	@SerialName("etag")
	val etag: String? = null,

	@SerialName("id")
	val id: String? = null,

	@SerialName("selfLink")
	val selfLink: String? = null
)

data class VolumeInfo(

	@SerialName("pageCount")
	val pageCount: Int? = null,

	@SerialName("printType")
	val printType: String? = null,

	@SerialName("previewLink")
	val previewLink: String? = null,

	@SerialName("canonicalVolumeLink")
	val canonicalVolumeLink: String? = null,

	@SerialName("description")
	val description: String? = null,

	@SerialName("language")
	val language: String? = null,

	@SerialName("title")
	val title: String? = null,

	@SerialName("imageLinks")
	val imageLinks: ImageLinks? = null,

	@SerialName("publisher")
	val publisher: String? = null,

	@SerialName("publishedDate")
	val publishedDate: String? = null,

	@SerialName("categories")
	val categories: List<String?>? = null,

	@SerialName("maturityRating")
	val maturityRating: String? = null,

	@SerialName("allowAnonLogging")
	val allowAnonLogging: Boolean? = null,

	@SerialName("contentVersion")
	val contentVersion: String? = null,

	@SerialName("authors")
	val authors: List<String?>? = null,

	@SerialName("infoLink")
	val infoLink: String? = null,

	@SerialName("subtitle")
	val subtitle: String? = null
)

data class ImageLinks(

	@SerialName("thumbnail")
	val thumbnail: String? = null,

	@SerialName("smallThumbnail")
	val smallThumbnail: String? = null
)

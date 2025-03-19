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

data class Pdf(

	@SerialName("isAvailable")
	val isAvailable: Boolean? = null,

	@SerialName("acsTokenLink")
	val acsTokenLink: String? = null
)

data class VolumeInfo(

	@SerialName("industryIdentifiers")
	val industryIdentifiers: List<IndustryIdentifiersItem?>? = null,

	@SerialName("pageCount")
	val pageCount: Int? = null,

	@SerialName("printType")
	val printType: String? = null,

	@SerialName("readingModes")
	val readingModes: ReadingModes? = null,

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

	@SerialName("panelizationSummary")
	val panelizationSummary: PanelizationSummary? = null,

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

data class ReadingModes(

	@SerialName("image")
	val image: Boolean? = null,

	@SerialName("text")
	val text: Boolean? = null
)

data class AccessInfo(

	@SerialName("accessViewStatus")
	val accessViewStatus: String? = null,

	@SerialName("country")
	val country: String? = null,

	@SerialName("viewability")
	val viewability: String? = null,

	@SerialName("pdf")
	val pdf: Pdf? = null,

	@SerialName("webReaderLink")
	val webReaderLink: String? = null,

	@SerialName("epub")
	val epub: Epub? = null,

	@SerialName("publicDomain")
	val publicDomain: Boolean? = null,

	@SerialName("quoteSharingAllowed")
	val quoteSharingAllowed: Boolean? = null,

	@SerialName("embeddable")
	val embeddable: Boolean? = null,

	@SerialName("textToSpeechPermission")
	val textToSpeechPermission: String? = null
)

data class IndustryIdentifiersItem(

	@SerialName("identifier")
	val identifier: String? = null,

	@SerialName("type")
	val type: String? = null
)

data class ImageLinks(

	@SerialName("thumbnail")
	val thumbnail: String? = null,

	@SerialName("smallThumbnail")
	val smallThumbnail: String? = null
)

data class ItemsItem(

	@SerialName("saleInfo")
	val saleInfo: SaleInfo? = null,

	@SerialName("searchInfo")
	val searchInfo: SearchInfo? = null,

	@SerialName("kind")
	val kind: String? = null,

	@SerialName("volumeInfo")
	val volumeInfo: VolumeInfo? = null,

	@SerialName("etag")
	val etag: String? = null,

	@SerialName("id")
	val id: String? = null,

	@SerialName("accessInfo")
	val accessInfo: AccessInfo? = null,

	@SerialName("selfLink")
	val selfLink: String? = null
)

data class RetailPrice(

	@SerialName("amount")
	val amount: Double? = null,

	@SerialName("currencyCode")
	val currencyCode: String? = null,

	@SerialName("amountInMicros")
	val amountInMicros: Int? = null
)

data class SearchInfo(

	@SerialName("textSnippet")
	val textSnippet: String? = null
)

data class OffersItem(

	@SerialName("finskyOfferType")
	val finskyOfferType: Int? = null,

	@SerialName("retailPrice")
	val retailPrice: RetailPrice? = null,

	@SerialName("listPrice")
	val listPrice: ListPrice? = null
)

data class SaleInfo(

	@SerialName("offers")
	val offers: List<OffersItem?>? = null,

	@SerialName("country")
	val country: String? = null,

	@SerialName("isEbook")
	val isEbook: Boolean? = null,

	@SerialName("saleability")
	val saleability: String? = null,

	@SerialName("buyLink")
	val buyLink: String? = null,

	@SerialName("retailPrice")
	val retailPrice: RetailPrice? = null,

	@SerialName("listPrice")
	val listPrice: ListPrice? = null
)

data class PanelizationSummary(

	@SerialName("containsImageBubbles")
	val containsImageBubbles: Boolean? = null,

	@SerialName("containsEpubBubbles")
	val containsEpubBubbles: Boolean? = null
)

data class ListPrice(

	@SerialName("amount")
	val amount: Double? = null,

	@SerialName("currencyCode")
	val currencyCode: String? = null,

	@SerialName("amountInMicros")
	val amountInMicros: Int? = null
)

data class Epub(

	@SerialName("isAvailable")
	val isAvailable: Boolean? = null,

	@SerialName("acsTokenLink")
	val acsTokenLink: String? = null
)

package com.example.surfbooksapp.data.mapper

import com.example.surfbooksapp.data.network.model.BookResponse
import com.example.surfbooksapp.data.network.model.ImageLinks
import com.example.surfbooksapp.data.network.model.ItemsItem
import com.example.surfbooksapp.data.network.model.VolumeInfo
import com.example.surfbooksapp.domain.model.Book
import com.example.surfbooksapp.domain.model.DomainImageLinks
import com.example.surfbooksapp.domain.model.DomainItemsItem

fun BookResponse.mapToDomainBook(): Book {
    return Book(this.totalItems, this.kind, this.items?.map { it?.mapToDomainItemsItem() })
}

fun List<BookResponse>.mapToDomainListBook(): List<Book> {
    return this.map { it.mapToDomainBook() }
}

fun ItemsItem.mapToDomainItemsItem(): DomainItemsItem {
    return DomainItemsItem(
        kind = this.kind,
        domainVolumeInfo = this.volumeInfo?.mapToDomainVolumeInfo(),
        etag = this.etag,
        id = this.id,
        selfLink = this.selfLink
    )
}

fun VolumeInfo.mapToDomainVolumeInfo(): com.example.surfbooksapp.domain.model.DomainVolumeInfo {
    return com.example.surfbooksapp.domain.model.DomainVolumeInfo(
        pageCount = this.pageCount,
        printType = this.printType,
        previewLink = this.previewLink,
        canonicalVolumeLink = this.canonicalVolumeLink,
        description = this.description,
        language = this.language,
        title = this.title,
        imageLinks = this.imageLinks?.mapToDomainImageLinks(),
        publisher = this.publisher,
        publishedDate = this.publishedDate,
        categories = this.categories,
        maturityRating = this.maturityRating,
        allowAnonLogging = this.allowAnonLogging,
        contentVersion = this.contentVersion,
        authors = this.authors,
        infoLink = this.infoLink,
        subtitle = this.subtitle
    )
}

fun ImageLinks.mapToDomainImageLinks(): DomainImageLinks {
    return DomainImageLinks(
        thumbnail = this.thumbnail,
        smallThumbnail = this.smallThumbnail
    )
}
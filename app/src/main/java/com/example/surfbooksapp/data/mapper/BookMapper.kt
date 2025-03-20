package com.example.surfbooksapp.data.mapper

import com.example.surfbooksapp.data.local.model.BookEntity
import com.example.surfbooksapp.data.local.model.ImageLinksLocal
import com.example.surfbooksapp.data.local.model.VolumeInfoLocal
import com.example.surfbooksapp.data.network.model.BookDto
import com.example.surfbooksapp.data.network.model.BookResponse
import com.example.surfbooksapp.data.network.model.ImageLinksDto
import com.example.surfbooksapp.data.network.model.VolumeInfoDto
import com.example.surfbooksapp.domain.model.Book
import com.example.surfbooksapp.domain.model.ImageLinks
import com.example.surfbooksapp.domain.model.VolumeInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun BookDto.mapToDomain() : Book {
    return Book(this.id, this.volumeInfo?.toDomain())
}

fun BookResponse.mapToDomainListBook() : List<Book> {
    return this.items.map { it.mapToDomain() }
}

fun BookEntity.mapToDomain() : Book {
    return Book(this.id, this.volumeInfoLocal?.toDomain(), this.isFavourite)
}

fun Book.mapToEntity() : BookEntity {
    return BookEntity(this.id, this.volumeInfo?.mapToVolumeInfoLocal(), this.isFavourite)
}

fun List<Book>.mapToEntity() : List<BookEntity> {
    return this.map { it.mapToEntity() }
}

fun List<BookEntity>.mapToDomainListBook() : List<Book> {
    return this.map { it.mapToDomain() }
}

fun Flow<List<BookEntity>>.mapToDomainListFlowBook() : Flow<List<Book>> {
    return this.map { it.mapToDomainListBook() }
}

fun VolumeInfoDto.toDomain(): VolumeInfo {
    return VolumeInfo(
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

fun VolumeInfoLocal.toDomain(): VolumeInfo {
    return VolumeInfo(
        pageCount = this.pageCount,
        printType = this.printType,
        previewLink = this.previewLink,
        canonicalVolumeLink = this.canonicalVolumeLink,
        description = this.description,
        language = this.language,
        title = this.title,
        imageLinks = this.imageLinksLocal?.mapToDomainImageLinks(),
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

fun VolumeInfo.mapToVolumeInfoDto(): VolumeInfoDto {
    return VolumeInfoDto(
        pageCount = this.pageCount,
        printType = this.printType,
        previewLink = this.previewLink,
        canonicalVolumeLink = this.canonicalVolumeLink,
        description = this.description,
        language = this.language,
        title = this.title,
        imageLinks = this.imageLinks?.mapToDtoImageLinks(),
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

fun VolumeInfo.mapToVolumeInfoLocal(): VolumeInfoLocal {
    return VolumeInfoLocal(
        pageCount = this.pageCount,
        printType = this.printType,
        previewLink = this.previewLink,
        canonicalVolumeLink = this.canonicalVolumeLink,
        description = this.description,
        language = this.language,
        title = this.title,
        imageLinksLocal = this.imageLinks?.mapToLocalImageLinks(),
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

fun ImageLinksLocal.mapToDomainImageLinks(): ImageLinks {
    return ImageLinks(
        thumbnail = this.thumbnail,
        smallThumbnail = this.smallThumbnail
    )
}

fun ImageLinks.mapToLocalImageLinks(): ImageLinksLocal {
    return ImageLinksLocal(
        thumbnail = this.thumbnail,
        smallThumbnail = this.smallThumbnail
    )
}

fun ImageLinksDto.mapToDomainImageLinks(): ImageLinks {
    return ImageLinks(
        thumbnail = this.thumbnail,
        smallThumbnail = this.smallThumbnail
    )
}

fun ImageLinks.mapToDtoImageLinks(): ImageLinksDto {
    return ImageLinksDto(
        thumbnail = this.thumbnail,
        smallThumbnail = this.smallThumbnail
    )
}
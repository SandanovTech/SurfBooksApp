package com.example.surfbooksapp.data.mapper

import com.example.surfbooksapp.data.network.model.BookDTO
import com.example.surfbooksapp.data.network.model.BookResponse
import com.example.surfbooksapp.domain.model.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun BookDTO.mapToDomainBook(): Book {
    return Book(this.q)
}

fun List<BookDTO>.mapToDomainBookList(): List<Book> {
    return this.map { it.mapToDomainBook() }
}

fun Flow<List<BookDTO>>.mapToDomainBookFlowList(): Flow<List<Book>> {
    return this.map { it.mapToDomainBookList() }
}

fun Book.mapToDtoBook() : BookDTO {
    return BookDTO(this.q)
}

fun List<Book>.mapToDtoBookList(): List<BookDTO> {
    return this.map { it.mapToDtoBook() }
}

fun Flow<List<Book>>.mapToDtoBookFlowList(): Flow<List<BookDTO>> {
    return this.map { it.mapToDtoBookList() }
}

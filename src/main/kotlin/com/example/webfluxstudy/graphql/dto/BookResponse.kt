package com.example.webfluxstudy.graphql.dto

import com.example.webfluxstudy.domain.entity.Book

data class BookResponse(
    val id: String?,
    val title: String,
    val author: String,
    val fullTitle: String
) {
    companion object {
        fun from(book: Book): BookResponse = BookResponse(
            id = book.id,
            title = book.title,
            author = book.author,
            fullTitle = "${book.title} by ${book.author}"
        )
    }
}
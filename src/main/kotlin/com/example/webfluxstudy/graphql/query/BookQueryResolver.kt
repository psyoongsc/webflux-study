package com.example.webfluxstudy.graphql.query

import com.example.webfluxstudy.graphql.dto.BookPageResponse
import com.example.webfluxstudy.graphql.dto.BookResponse
import com.example.webfluxstudy.domain.service.BookService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class BookQueryResolver(
    private val bookService: BookService
) {
    @QueryMapping
    suspend fun books(
        @Argument offset: Int,
        @Argument limit: Int,
        @Argument titleContains: String?
    ): BookPageResponse {
        val result = bookService.findFilteredBooks(offset, limit, titleContains)
        return BookPageResponse.from(result)
    }

    @QueryMapping
    suspend fun book(
        @Argument id: String
    ): BookResponse {
        val book = bookService.findById(id)
        return BookResponse.from(book)
    }
}
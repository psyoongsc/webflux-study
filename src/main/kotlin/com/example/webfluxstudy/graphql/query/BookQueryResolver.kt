package com.example.webfluxstudy.graphql.query

import com.example.webfluxstudy.graphql.dto.BookPageResponse
import com.example.webfluxstudy.graphql.dto.BookResponse
import com.example.webfluxstudy.service.BookService
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
        @Argument limit: Int
    ): BookPageResponse {
        val result = bookService.findAll(offset, limit)
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
package com.example.webfluxstudy.graphql.mutation

import com.example.webfluxstudy.graphql.dto.BookInput
import com.example.webfluxstudy.graphql.dto.BookResponse
import com.example.webfluxstudy.domain.service.BookService
import jakarta.validation.Valid
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller

@Controller
class BookMutationResolver(
    private val bookService: BookService
) {
    @MutationMapping
    suspend fun createBook(@Argument @Valid input: BookInput): BookResponse {
        val created = bookService.createBook(input.toCommand())
        return BookResponse.from(created)
    }
}
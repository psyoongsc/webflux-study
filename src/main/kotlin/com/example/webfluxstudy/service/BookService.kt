package com.example.webfluxstudy.service

import com.example.webfluxstudy.domain.Book
import com.example.webfluxstudy.repository.BookRepository
import com.example.webfluxstudy.service.dto.BookListResult
import com.example.webfluxstudy.service.dto.CreateBookCommand
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.stereotype.Service

@Service
class BookService(
    private val bookRepository: BookRepository
) {
    suspend fun findAll(offset: Int, limit: Int): BookListResult {
        val total = bookRepository.count().awaitSingle()
        val items = bookRepository.findAll()
            .skip(offset.toLong())
            .take(limit.toLong())
            .collectList()
            .awaitSingle()

        return BookListResult(total = total.toInt(), items = items)
    }

    suspend fun findById(id: String): Book {
        val book = bookRepository.findById(id)
            .awaitSingleOrNull()
            ?: throw IllegalArgumentException("Book with ID $id not found")

        return book
    }

    suspend fun createBook(command: CreateBookCommand): Book {
        val book = Book(title = command.title, author = command.author)
        return bookRepository.save(book).awaitFirst()
    }
}
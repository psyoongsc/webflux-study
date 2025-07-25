package com.example.webfluxstudy.domain.service

import com.example.webfluxstudy.domain.entity.Book
import com.example.webfluxstudy.domain.repository.BookRepository
import com.example.webfluxstudy.domain.dto.BookListResult
import com.example.webfluxstudy.domain.dto.CreateBookCommand
import org.springframework.stereotype.Service

@Service
class BookService(
    private val bookRepository: BookRepository
) {
    suspend fun findAll(offset: Int, limit: Int): BookListResult {
        val total = bookRepository.count()
        val items = bookRepository.findAll(offset, limit)

        return BookListResult(total = total.toInt(), items = items)
    }

    suspend fun findById(id: String): Book {
        val book = bookRepository.findById(id)
            ?: throw IllegalArgumentException("Book with ID $id not found")

        return book
    }

    suspend fun findFilteredBooks(offset: Int, limit: Int, titleContains: String?): BookListResult {
        val total = bookRepository.count()
        val items = bookRepository.findWithTitle(titleContains, offset, limit)

        return BookListResult(total = total.toInt(), items = items)
    }

    suspend fun createBook(command: CreateBookCommand): Book {
        val book = Book(title = command.title, author = command.author)
        return bookRepository.save(book)
    }
}
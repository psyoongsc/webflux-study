package com.example.webfluxstudy.repository

import com.example.webfluxstudy.domain.Book
import org.springframework.stereotype.Repository

@Repository
interface BookRepository {
    suspend fun findById(id: String): Book?
    suspend fun findAll(offset: Int, limit: Int): List<Book>
    suspend fun count(): Long
    suspend fun save(book: Book): Book
}
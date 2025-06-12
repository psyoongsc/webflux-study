package com.example.webfluxstudy.repository

import com.example.webfluxstudy.domain.Book
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : ReactiveCrudRepository<Book, String>
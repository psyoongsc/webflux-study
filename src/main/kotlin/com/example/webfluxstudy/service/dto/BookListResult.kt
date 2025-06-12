package com.example.webfluxstudy.service.dto

import com.example.webfluxstudy.domain.Book

data class BookListResult(
    val total: Int,
    val items: List<Book>
)

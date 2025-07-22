package com.example.webfluxstudy.domain.dto

import com.example.webfluxstudy.domain.entity.Book

data class BookListResult(
    val total: Int,
    val items: List<Book>
)

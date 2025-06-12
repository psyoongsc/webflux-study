package com.example.webfluxstudy.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("books")
data class Book(
    @Id val id: String? = null,
    val title: String,
    val author: String
)

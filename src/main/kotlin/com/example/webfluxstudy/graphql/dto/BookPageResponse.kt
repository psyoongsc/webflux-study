package com.example.webfluxstudy.graphql.dto

import com.example.webfluxstudy.domain.dto.BookListResult

data class BookPageResponse(
    val total: Int,
    val items: List<BookResponse>
) {
    companion object {
        fun from(result: BookListResult) = BookPageResponse(result.total.toInt(), result.items.map{ BookResponse.from(it) })
    }
}

package com.example.webfluxstudy.graphql.dto

import com.example.webfluxstudy.domain.dto.CreateBookCommand
import jakarta.validation.constraints.NotBlank

data class BookInput(
    @field:NotBlank(message = "제목은 필수입니다.")
    val title: String,

    @field:NotBlank(message = "저자는 필수입니다.")
    val author: String
) {
    fun toCommand() = CreateBookCommand(title, author)
}
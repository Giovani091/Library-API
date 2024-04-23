package com.library.api2.model.dto.openLibrary

data class BookDTO(
    val title: String,
    val description: String? = null,
    val author: String? = null
)
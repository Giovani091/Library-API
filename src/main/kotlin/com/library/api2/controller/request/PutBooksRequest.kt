package com.library.api2.controller.request

import jakarta.validation.constraints.NotEmpty

data class PutBooksRequest (

    @field:NotEmpty
    var title: String,

    @field:NotEmpty
    var author: String,

    @field:NotEmpty
    var category: String
)
package com.library.api2.controller.request

data class PutBooksRequest (
    var title: String,

    var author: String,

    var category: String
)
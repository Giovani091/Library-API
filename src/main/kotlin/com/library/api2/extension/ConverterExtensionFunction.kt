package com.library.api2.extension

import com.library.api2.controller.request.PostBooksRequest
import com.library.api2.controller.request.PutBooksRequest
import com.library.api2.model.BooksModel

fun PostBooksRequest.toBooksModel(): BooksModel {
    return BooksModel(title = this.title, author = this.author, category = this.category)
}

fun PutBooksRequest.toBooksModel(id: Int): BooksModel{
    return BooksModel(id = id, title = this.title, author = this.author, category = this.category)
}
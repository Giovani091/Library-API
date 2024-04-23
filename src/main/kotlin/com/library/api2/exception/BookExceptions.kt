package com.library.api2.exception

class BookNotFoundException(override val message: String, val errorCode: String): Exception() {
}

class BookAlreadyExistsException() : Exception()

class BookTitleAlreadyExistsException() : Exception()
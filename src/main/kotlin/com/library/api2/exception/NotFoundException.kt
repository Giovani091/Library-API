package com.library.api2.exception

class NotFoundException(override val message: String, val errorCode: String): Exception() {
}
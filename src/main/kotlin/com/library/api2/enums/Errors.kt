package com.library.api2.enums

enum class Errors(val code: String, val message: String) {

    LA001("LA-001", "Book [%s] not exists"),
    LA101("LA-101", "Invalid Request")
}
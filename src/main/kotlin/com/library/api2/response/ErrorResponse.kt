package com.library.api2.response

data class ErrorResponse(
    var httpCode: Int,
    var message: String,
    var internalCode: String,

)
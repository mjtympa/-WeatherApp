package com.mujeeb.weatherapp.data.enums

data class ErrorModel(
    val errorType: ErrorType,
    val errorMessage: String? = null,
)

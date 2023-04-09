package com.plcoding.stockmarketapp.domain.model

import com.squareup.moshi.Json

data class ErrorMessage(
    @field:Json(name = "Error Message") val errorMessage: String
)
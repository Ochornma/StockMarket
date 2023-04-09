package com.plcoding.stockmarketapp.data.remote.dto

import com.squareup.moshi.Json

data class ErrorData(
    @field:Json(name = "Error Message") val errorMessage: String?
)
package com.plcoding.stockmarketapp.data.mapper

import com.plcoding.stockmarketapp.data.local.CompanyListingEntity
import com.plcoding.stockmarketapp.data.remote.dto.CompanyInfoDto
import com.plcoding.stockmarketapp.data.remote.dto.ErrorData
import com.plcoding.stockmarketapp.domain.model.CompanyInfo
import com.plcoding.stockmarketapp.domain.model.CompanyListing
import com.plcoding.stockmarketapp.domain.model.ErrorMessage
import com.plcoding.stockmarketapp.util.Resource
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import retrofit2.HttpException

fun CompanyListingEntity.toCompanyListing(): CompanyListing {
    return CompanyListing(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity {
    return CompanyListingEntity(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyInfoDto.toCompanyInfo(): CompanyInfo {
    return CompanyInfo(
        symbol = symbol ?: "",
        description = description ?: "",
        name = name ?: "",
        country = country ?: "",
        industry = industry ?: ""
    )
}

fun ErrorData.toErrorMessage(defaultMessage: String): ErrorMessage {
    return ErrorMessage(
        errorMessage = errorMessage ?: defaultMessage
    )
}

fun defaultErrorData(): ErrorData {
    return  ErrorData(errorMessage = "Unable to load data")
}

fun returnErrorMessage(e: HttpException): ErrorMessage {
    val json = e.response()?.errorBody()?.string() ?: ""
        val moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<ErrorData> = moshi.adapter(ErrorData::class.java)
        val errorData = jsonAdapter.fromJson(json) ?: defaultErrorData()
        return errorData.toErrorMessage("Couldn't load company info")
}
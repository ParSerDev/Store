package com.parserdev.store.data.repository.details

import com.parserdev.store.domain.models.details.PhoneDetails
import com.parserdev.store.domain.network.NetworkResult

interface DetailsRepository {
    suspend fun getPhoneDetails(url: String): NetworkResult<PhoneDetails?>
    //suspend fun getComputerDetails(url: String): NetworkResult<ComputerDetails?>
    //suspend fun getHealthDetails(url: String): NetworkResult<HealthDetails?>
    //suspend fun getBookDetails(url: String): NetworkResult<BookDetails?>
    //suspend fun getToolDetails(url: String): NetworkResult<ToolDetails?>
}
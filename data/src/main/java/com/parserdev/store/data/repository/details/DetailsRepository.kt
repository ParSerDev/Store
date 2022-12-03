package com.parserdev.store.data.repository.details

import com.parserdev.store.domain.models.details.PhoneDetails
import com.parserdev.store.domain.network.NetworkResult

interface DetailsRepository {
    suspend fun getPhoneDetails(id: Int): NetworkResult<PhoneDetails?>
    //suspend fun getComputerDetails(id: Int): NetworkResult<ComputerDetails?>
    //suspend fun getHealthDetails(id: Int): NetworkResult<HealthDetails?>
    //suspend fun getBookDetails(id: Int): NetworkResult<BookDetails?>
    //suspend fun getToolDetails(id: Int): NetworkResult<ToolDetails?>
}
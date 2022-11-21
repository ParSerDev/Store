package com.parserdev.store.data.repository.details

import com.parserdev.store.data.network.NetworkInstance
import com.parserdev.store.data.utils.safeApiCall
import com.parserdev.store.domain.models.details.PhoneDetails
import com.parserdev.store.domain.network.NetworkResult
import javax.inject.Inject

class DetailsRepositoryImpl @Inject constructor(
    private val networkInstance: NetworkInstance
) : DetailsRepository {

    override suspend fun getPhoneDetails(url: String): NetworkResult<PhoneDetails?> {
        return when (val networkResult =
            safeApiCall { networkInstance.detailsService.getPhoneDetailsDto(url = PHONE_DETAILS_URL) }) {
            is NetworkResult.Success -> NetworkResult.Success(data = networkResult.data?.mapToDomainModel())
            is NetworkResult.Error -> NetworkResult.Error(
                message = networkResult.message,
                data = networkResult.data?.mapToDomainModel()
            )
            is NetworkResult.Loading -> NetworkResult.Loading(data = networkResult.data?.mapToDomainModel())
        }
    }

}

private const val PHONE_DETAILS_URL = "6c14c560-15c6-4248-b9d2-b4508df7d4f5"
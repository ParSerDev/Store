package com.parserdev.store.data.repository.details

import com.parserdev.store.data.mapper.details.SmartphoneDetailsMapperImpl
import com.parserdev.store.data.network.NetworkInstance
import com.parserdev.store.data.utils.safeApiCall
import com.parserdev.store.domain.models.details.SmartphoneDetails
import com.parserdev.store.domain.network.NetworkResult
import javax.inject.Inject

class DetailsRepositoryImpl @Inject constructor(
    private val networkInstance: NetworkInstance
) : DetailsRepository {

    override suspend fun getPhoneDetails(id: Int): NetworkResult<SmartphoneDetails?> {
        return safeApiCall(apiToBeCalled = { networkInstance.detailsService.getPhoneDetailsDto(url = PHONE_DETAILS_URL) },
            mapper = { dto -> SmartphoneDetailsMapperImpl().toSmartphoneDetailsModel(dto) })
    }

}

private const val PHONE_DETAILS_URL = "6c14c560-15c6-4248-b9d2-b4508df7d4f5"
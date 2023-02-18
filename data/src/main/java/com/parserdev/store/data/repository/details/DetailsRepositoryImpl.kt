package com.parserdev.store.data.repository.details

import com.parserdev.store.data.dto.details.SmartphoneDetailsDto
import com.parserdev.store.data.mapper.Mapper
import com.parserdev.store.data.mapper.details.SmartphoneDetailsMapper
import com.parserdev.store.data.network.NetworkInstance
import com.parserdev.store.data.utils.safeApiCall
import com.parserdev.store.domain.models.details.SmartphoneDetails
import com.parserdev.store.domain.network.NetworkResult
import javax.inject.Inject

class DetailsRepositoryImpl @Inject constructor(
    private val networkInstance: NetworkInstance,
    private val smartphoneDetailsMapper: Mapper<SmartphoneDetailsDto, SmartphoneDetails>
) : DetailsRepository {

    override suspend fun getPhoneDetails(id: Int): NetworkResult<SmartphoneDetails?> {
        return mapPhoneDetails(dto = safeApiCall(apiToBeCalled = {
            networkInstance.detailsService.getPhoneDetailsDto(
                url = PHONE_DETAILS_URL
            )
        }))
    }

    private fun mapPhoneDetails(dto: NetworkResult<SmartphoneDetailsDto?>): NetworkResult<SmartphoneDetails?> {
        return when (dto) {
            is NetworkResult.Success -> NetworkResult.Success(
                smartphoneDetailsMapper.map(
                    dto = dto.data!!
                )
            )
            is NetworkResult.Error -> NetworkResult.Error(message = dto.message)
            is NetworkResult.Loading -> NetworkResult.Loading()
        }
    }

}

private const val PHONE_DETAILS_URL = "6c14c560-15c6-4248-b9d2-b4508df7d4f5"
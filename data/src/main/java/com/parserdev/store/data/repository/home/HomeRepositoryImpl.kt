package com.parserdev.store.data.repository.home

import com.parserdev.store.data.dto.home.HomePageDto
import com.parserdev.store.data.mapper.Mapper
import com.parserdev.store.data.mapper.home.HomeMapper
import com.parserdev.store.data.network.NetworkInstance
import com.parserdev.store.data.utils.safeApiCall
import com.parserdev.store.domain.models.home.HomeCategory
import com.parserdev.store.domain.models.home.HomePage
import com.parserdev.store.domain.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val networkInstance: NetworkInstance,
    private val homeMapper: Mapper<HomePageDto, HomePage>
) : HomeRepository {

    override suspend fun getHomePage(homeCategory: HomeCategory): Flow<NetworkResult<HomePage?>> {
        return flow {
            when (homeCategory) {
                HomeCategory.PHONES -> emit(
                    mapHomePage(dto = safeApiCall(
                        apiToBeCalled = { networkInstance.homeService.getHomePageDto(url = PHONE_HOME_PAGE_URL) }
                    )))
                HomeCategory.COMPUTERS -> emit(NetworkResult.Error(message = NO_DATA))
                HomeCategory.HEALTH -> emit(NetworkResult.Error(message = NO_DATA))
                HomeCategory.BOOKS -> emit(NetworkResult.Error(message = NO_DATA))
                HomeCategory.TOOLS -> emit(NetworkResult.Error(message = NO_DATA))
            }
        }
    }

    private fun mapHomePage(dto: NetworkResult<HomePageDto?>): NetworkResult<HomePage?> {
        return when (dto) {
            is NetworkResult.Success -> NetworkResult.Success(homeMapper.map(dto = dto.data!!))
            is NetworkResult.Error -> NetworkResult.Error(message = dto.message)
            is NetworkResult.Loading -> NetworkResult.Loading()
        }
    }


}

private const val NO_DATA = "No data"
private const val PHONE_HOME_PAGE_URL = "654bd15e-b121-49ba-a588-960956b15175"
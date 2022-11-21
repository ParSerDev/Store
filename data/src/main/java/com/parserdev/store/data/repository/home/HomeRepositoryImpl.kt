package com.parserdev.store.data.repository.home

import com.parserdev.store.data.database.StoreDatabase
import com.parserdev.store.data.dto.home.FavouriteItemDto
import com.parserdev.store.data.network.NetworkInstance
import com.parserdev.store.data.utils.safeApiCall
import com.parserdev.store.domain.models.home.HomeCategory
import com.parserdev.store.domain.models.home.HomePage
import com.parserdev.store.domain.network.NetworkResult
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val networkInstance: NetworkInstance, private val database: StoreDatabase
) : HomeRepository {

    override suspend fun getHomePage(homeCategory: HomeCategory): NetworkResult<HomePage?> {
        return when (homeCategory) {
            HomeCategory.PHONES -> when (val networkResult =
                safeApiCall { networkInstance.homeService.getHomePageDto(url = PHONE_HOME_PAGE_URL) }) {
                is NetworkResult.Success -> NetworkResult.Success(data = networkResult.data?.mapToDomainModel())
                is NetworkResult.Error -> NetworkResult.Error(
                    message = networkResult.message, data = networkResult.data?.mapToDomainModel()
                )
                is NetworkResult.Loading -> NetworkResult.Loading(data = networkResult.data?.mapToDomainModel())
            }
            HomeCategory.COMPUTERS -> NetworkResult.Error(message = NO_DATA)
            HomeCategory.HEALTH -> NetworkResult.Error(message = NO_DATA)
            HomeCategory.BOOKS -> NetworkResult.Error(message = NO_DATA)
            HomeCategory.TOOLS -> NetworkResult.Error(message = NO_DATA)
        }
    }

    override suspend fun insertFavouriteItemDto(favouriteItemDto: FavouriteItemDto) {
        database.homeDao().insertFavouriteItemDto(favouriteItemDto = favouriteItemDto)
    }

    override suspend fun deleteFavouriteItemDto(favouriteItemDto: FavouriteItemDto) {
        database.homeDao().deleteFavouriteItemDto(favouriteItemDto = favouriteItemDto)
    }

    override suspend fun isFavouriteItemDtoExists(id: Int, homeCategory: HomeCategory) =
        database.homeDao().isFavouriteItemDtoExists(id = id, homeCategory = homeCategory)

}

private const val NO_DATA = "No data"
private const val PHONE_HOME_PAGE_URL = "654bd15e-b121-49ba-a588-960956b15175"
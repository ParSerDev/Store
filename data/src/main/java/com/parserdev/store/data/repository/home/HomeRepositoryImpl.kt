package com.parserdev.store.data.repository.home

import com.parserdev.store.data.database.StoreDatabase
import com.parserdev.store.data.dto.home.FavouriteItemDto
import com.parserdev.store.data.network.NetworkInstance
import com.parserdev.store.data.utils.safeApiCall
import com.parserdev.store.domain.models.home.HomeCategory
import com.parserdev.store.domain.models.home.HomePage
import com.parserdev.store.domain.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val networkInstance: NetworkInstance, private val database: StoreDatabase
) : HomeRepository {

    override suspend fun getHomePage(homeCategory: HomeCategory): Flow<NetworkResult<HomePage?>> {
        val networkResult =
            safeApiCall { networkInstance.homeService.getHomePageDto(url = PHONE_HOME_PAGE_URL) }
        return flow {
            when (homeCategory) {
                HomeCategory.PHONES -> when (networkResult) {
                    is NetworkResult.Success -> emit(
                        NetworkResult.Success(
                            data = networkResult.data?.mapToDomainModel()
                        )
                    )
                    is NetworkResult.Error -> emit(
                        NetworkResult.Error(
                            message = networkResult.message
                        )
                    )
                    is NetworkResult.Loading -> emit(NetworkResult.Loading(data = networkResult.data?.mapToDomainModel()))
                }
                HomeCategory.COMPUTERS -> emit(NetworkResult.Error(message = NO_DATA))
                HomeCategory.HEALTH -> emit(NetworkResult.Error(message = NO_DATA))
                HomeCategory.BOOKS -> emit(NetworkResult.Error(message = NO_DATA))
                HomeCategory.TOOLS -> emit(NetworkResult.Error(message = NO_DATA))
            }
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
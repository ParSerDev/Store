package com.parserdev.store.data.repository.home

import com.parserdev.store.data.dto.home.FavouriteItemDto
import com.parserdev.store.domain.models.home.HomeCategory
import com.parserdev.store.domain.models.home.HomePage
import com.parserdev.store.domain.network.NetworkResult

interface HomeRepository {
    suspend fun getHomePage(homeCategory: HomeCategory): NetworkResult<HomePage?>
    suspend fun insertFavouriteItemDto(favouriteItemDto: FavouriteItemDto)
    suspend fun deleteFavouriteItemDto(favouriteItemDto: FavouriteItemDto)
    suspend fun isFavouriteItemDtoExists(id: Int, homeCategory: HomeCategory): Boolean
}
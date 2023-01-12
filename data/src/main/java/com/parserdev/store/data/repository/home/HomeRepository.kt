package com.parserdev.store.data.repository.home

import com.parserdev.store.domain.models.home.CartItemsAmount
import com.parserdev.store.domain.models.home.HomeCategory
import com.parserdev.store.domain.models.home.HomePage
import com.parserdev.store.domain.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getHomePage(homeCategory: HomeCategory): Flow<NetworkResult<HomePage?>>
}
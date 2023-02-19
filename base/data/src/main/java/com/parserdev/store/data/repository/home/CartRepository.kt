package com.parserdev.store.data.repository.home

import com.parserdev.store.domain.models.cart.CartContent
import com.parserdev.store.domain.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun getCartContent(): Flow<NetworkResult<CartContent?>>
}
package com.parserdev.store.data.repository.home.cart

import com.parserdev.store.domain.models.home.cart.CartContent
import com.parserdev.store.domain.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun getCartContent(): Flow<NetworkResult<CartContent?>>
}
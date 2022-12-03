package com.parserdev.store.data.repository.cart

import com.parserdev.store.domain.models.cart.CartContent
import com.parserdev.store.domain.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun getCartContent(): NetworkResult<CartContent?>
}
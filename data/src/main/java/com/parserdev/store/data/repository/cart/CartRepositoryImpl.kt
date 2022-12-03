package com.parserdev.store.data.repository.cart

import com.parserdev.store.data.network.NetworkInstance
import com.parserdev.store.data.utils.safeApiCall
import com.parserdev.store.domain.models.cart.CartContent
import com.parserdev.store.domain.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val networkInstance: NetworkInstance
) : CartRepository {

    override suspend fun getCartContent(): NetworkResult<CartContent?> {
        val networkResult =
            safeApiCall { networkInstance.cartService.getCartContentDto(url = CART_CONTENT_URL) }
        return when (networkResult) {
            is NetworkResult.Success ->
                NetworkResult.Success(
                    data = networkResult.data?.mapToDomainModel()
                )

            is NetworkResult.Error ->
                NetworkResult.Error(
                    message = networkResult.message
                )

            is NetworkResult.Loading -> NetworkResult.Loading(data = networkResult.data?.mapToDomainModel())
        }
    }

}

private const val CART_CONTENT_URL = "53539a72-3c5f-4f30-bbb1-6ca10d42c149"
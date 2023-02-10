package com.parserdev.store.data.repository.home.cart

import com.parserdev.store.data.mapper.home.cart.CartMapper
import com.parserdev.store.data.network.NetworkInstance
import com.parserdev.store.data.utils.safeApiCall
import com.parserdev.store.domain.models.home.cart.CartContent
import com.parserdev.store.domain.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val networkInstance: NetworkInstance,
    private val cartMapper: CartMapper
) : CartRepository {

    override suspend fun getCartContent(): Flow<NetworkResult<CartContent?>> {
        return flow {
            emit(
                safeApiCall(
                    apiToBeCalled = { networkInstance.cartService.getCartContentDto(url = CART_CONTENT_URL) },
                    mapper = { dto -> cartMapper.toCartContentModel(dto) })
            )
        }
    }

}

private const val CART_CONTENT_URL = "53539a72-3c5f-4f30-bbb1-6ca10d42c149"
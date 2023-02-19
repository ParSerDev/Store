package com.parserdev.store.data.repository.home

import com.parserdev.store.data.dto.cart.CartContentDto
import com.parserdev.store.data.mapper.Mapper
import com.parserdev.store.data.network.NetworkInstance
import com.parserdev.store.data.utils.safeApiCall
import com.parserdev.store.domain.models.cart.CartContent
import com.parserdev.store.domain.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val networkInstance: NetworkInstance,
    private val cartMapper: Mapper<CartContentDto, CartContent>
) : CartRepository {

    override suspend fun getCartContent(): Flow<NetworkResult<CartContent?>> {
        return flow {
            emit(
                mapCartContent(dto = safeApiCall(
                    apiToBeCalled = { networkInstance.cartService.getCartContentDto(url = CART_CONTENT_URL) }
                )))
        }
    }

    private fun mapCartContent(dto: NetworkResult<CartContentDto?>): NetworkResult<CartContent?> {
        return when (dto) {
            is NetworkResult.Success -> NetworkResult.Success(
                cartMapper.map(
                    dto = dto.data!!
                )
            )
            is NetworkResult.Error -> NetworkResult.Error(message = dto.message)
            is NetworkResult.Loading -> NetworkResult.Loading()
        }
    }

}

private const val CART_CONTENT_URL = "53539a72-3c5f-4f30-bbb1-6ca10d42c149"
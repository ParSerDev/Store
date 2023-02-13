package com.parserdev.store.data.network.retrofit

import com.parserdev.store.data.dto.cart.CartContentDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CartService {
    @GET("{url}")
    suspend fun getCartContentDto(
        @Path("url") url: String
    ): Response<CartContentDto?>
}
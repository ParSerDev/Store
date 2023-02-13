package com.parserdev.store.data.network.retrofit

import com.parserdev.store.data.dto.details.SmartphoneDetailsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsService {
    @GET("{url}")
    suspend fun getPhoneDetailsDto(
        @Path("url") url: String
    ): Response<SmartphoneDetailsDto?>
}
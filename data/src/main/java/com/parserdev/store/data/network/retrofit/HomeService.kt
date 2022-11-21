package com.parserdev.store.data.network.retrofit

import com.parserdev.store.data.dto.HomePageDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeService {
    @GET("{url}")
    suspend fun getHomePageDto(
        @Path("url") url: String
    ): Response<HomePageDto>
}
package com.parserdev.store.data.network.retrofit

import com.parserdev.store.data.network.NetworkInstance
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitInstance @Inject constructor() : NetworkInstance {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    override val homeService: HomeService by lazy {
        retrofit.create(HomeService::class.java)
    }

    override val detailsService: DetailsService by lazy {
        retrofit.create(DetailsService::class.java)
    }

}
package com.parserdev.store.data.network

import com.parserdev.store.data.network.retrofit.DetailsService
import com.parserdev.store.data.network.retrofit.HomeService

interface NetworkInstance {
    val homeService: HomeService
    val detailsService: DetailsService
}
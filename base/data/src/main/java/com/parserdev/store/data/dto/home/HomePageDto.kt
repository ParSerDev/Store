package com.parserdev.store.data.dto.home

import com.google.gson.annotations.SerializedName

data class HomePageDto(
    @field:SerializedName("best_seller")
    val bestSeller: List<BestSellerItemDto>? = null,

    @field:SerializedName("home_store")
    val homeStore: List<HotItemDto>? = null
)
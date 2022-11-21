package com.parserdev.store.domain.models.home

data class HomePage(
    val bestSeller: List<BestSellerItem?>? = null,
    val homeStore: List<HomeStoreItem?>? = null
)

package com.parserdev.store.domain.models.home

data class HomePage(
    val bestSeller: List<BestSellerItem>?,
    val hotItems: List<HotItem>?
)

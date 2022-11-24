package com.parserdev.store.domain.models.home

data class HomePage(
    val bestSellers: List<BestSellerItem>?,
    val hotItems: List<HotItem>?
)

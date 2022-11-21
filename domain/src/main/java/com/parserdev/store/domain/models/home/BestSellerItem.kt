package com.parserdev.store.domain.models.home

data class BestSellerItem(

    val isFavorites: Boolean? = null,
    val discountPrice: Int? = null,
    val id: Int? = null,
    val title: String? = null,
    val priceWithoutDiscount: Int? = null,
    val picture: String? = null
)
package com.parserdev.store.data.dto.home.cart

import com.google.gson.annotations.SerializedName
import com.parserdev.store.domain.models.home.BestSellerItem
import com.parserdev.store.domain.models.home.CartItemsAmount
import com.parserdev.store.domain.models.home.cart.CartContent

data class CartContentDto(

    @field:SerializedName("basket")
    val cartItemDtos: List<CartItemDto>? = null,

    @field:SerializedName("delivery")
    val delivery: String? = null,

    @field:SerializedName("total")
    val total: Int? = null,

    @field:SerializedName("id")
    val id: String? = null
)
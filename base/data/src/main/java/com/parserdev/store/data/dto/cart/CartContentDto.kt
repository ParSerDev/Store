package com.parserdev.store.data.dto.cart

import com.google.gson.annotations.SerializedName

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
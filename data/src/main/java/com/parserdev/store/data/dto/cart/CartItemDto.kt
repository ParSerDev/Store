package com.parserdev.store.data.dto.cart

import com.google.gson.annotations.SerializedName
import com.parserdev.store.domain.models.home.BestSellerItem
import com.parserdev.store.domain.models.cart.CartItem

data class CartItemDto(

    @field:SerializedName("images")
    val images: String? = null,

    @field:SerializedName("price")
    val price: Int? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("amount")
    val amount: Int? = null
) {
    fun mapToDomainModel() = CartItem(
        images, price, id, title, amount
    )
}

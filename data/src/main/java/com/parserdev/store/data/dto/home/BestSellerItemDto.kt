package com.parserdev.store.data.dto.home

import com.google.gson.annotations.SerializedName
import com.parserdev.store.domain.models.home.BestSellerItem

data class BestSellerItemDto(

    @field:SerializedName("is_favorites")
    val isFavorites: Boolean? = null,

    @field:SerializedName("discount_price")
    val discountPrice: Int? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("price_without_discount")
    val priceWithoutDiscount: Int? = null,

    @field:SerializedName("picture")
    val picture: String? = null
) {
    fun mapToDomainModel() = BestSellerItem(
        isFavorites, discountPrice, id, title, priceWithoutDiscount, picture
    )
}
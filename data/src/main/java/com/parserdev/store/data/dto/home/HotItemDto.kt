package com.parserdev.store.data.dto.home

import com.google.gson.annotations.SerializedName
import com.parserdev.store.domain.models.home.HomeStoreItem
import com.parserdev.store.domain.models.home.HotItem

data class HotItemDto(

    @field:SerializedName("subtitle")
    val subtitle: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("picture")
    val picture: String? = null,

    @field:SerializedName("is_buy")
    val isBuy: Boolean? = null,

    @field:SerializedName("is_new")
    val isNew: Boolean? = null
) {
    fun mapToDomainModel() = HotItem(subtitle, id, title, picture, isBuy, isNew)
}
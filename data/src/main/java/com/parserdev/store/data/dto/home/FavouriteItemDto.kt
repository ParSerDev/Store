package com.parserdev.store.data.dto.home

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.parserdev.store.domain.models.home.HomeCategory

@Entity(tableName = "favouriteItem")
class FavouriteItemDto(
    @PrimaryKey
    @field:SerializedName("id")
    val id: Int,
    val homeCategory: HomeCategory
)
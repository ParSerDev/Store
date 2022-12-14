package com.parserdev.store.data.dto.details

import com.google.gson.annotations.SerializedName
import com.parserdev.store.domain.models.details.SmartphoneDetails

data class SmartphoneDetailsDto(

    @field:SerializedName("sd")
    val sd: String? = null,

    @field:SerializedName("images")
    val images: List<String?>? = null,

    @field:SerializedName("color")
    val color: List<String?>? = null,

    @field:SerializedName("ssd")
    val ssd: String? = null,

    @field:SerializedName("price")
    val price: Int? = null,

    @field:SerializedName("rating")
    val rating: Double? = null,

    @field:SerializedName("CPU")
    val cPU: String? = null,

    @field:SerializedName("isFavorites")
    val isFavorites: Boolean? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("camera")
    val camera: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("capacity")
    val capacity: List<String?>? = null
)

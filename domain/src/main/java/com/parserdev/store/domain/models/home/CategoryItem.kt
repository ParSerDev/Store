package com.parserdev.store.domain.models.home

class CategoryItem(
    val name: String,
    val iconActiveId: Int,
    val iconInactiveId: Int,
    val backgroundActiveId: Int,
    val backgroundInactiveId: Int,
    val textColorActiveId: Int,
    val textColorInactiveId: Int,
    val homeCategory: HomeCategory
)
package com.parserdev.store.data.mapper.home

import com.parserdev.store.data.dto.home.BestSellerItemDto
import com.parserdev.store.data.dto.home.HomePageDto
import com.parserdev.store.data.dto.home.HotItemDto
import com.parserdev.store.domain.models.home.BestSellerItem
import com.parserdev.store.domain.models.home.HomePage
import com.parserdev.store.domain.models.home.HotItem

interface HomeMapper {
    fun toHomePageModel(homePageDto: HomePageDto): HomePage
    fun toBestSellerItemModel(bestSellerItemDto: BestSellerItemDto): BestSellerItem
    fun toHotItemModel(hotItemDto: HotItemDto): HotItem
}
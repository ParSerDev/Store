package com.parserdev.store.data.mapper

import com.parserdev.store.data.dto.home.BestSellerItemDto
import com.parserdev.store.data.dto.home.HomePageDto
import com.parserdev.store.data.dto.home.HotItemDto
import com.parserdev.store.domain.models.home.BestSellerItem
import com.parserdev.store.domain.models.home.HomePage
import com.parserdev.store.domain.models.home.HotItem

interface Mapper<T,R>{
    fun map(dto: T): R
}
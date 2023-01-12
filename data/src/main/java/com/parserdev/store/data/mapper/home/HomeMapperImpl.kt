package com.parserdev.store.data.mapper.home

import com.parserdev.store.data.dto.home.BestSellerItemDto
import com.parserdev.store.data.dto.home.HomePageDto
import com.parserdev.store.data.dto.home.HotItemDto
import com.parserdev.store.domain.models.home.BestSellerItem
import com.parserdev.store.domain.models.home.HomePage
import com.parserdev.store.domain.models.home.HotItem
import javax.inject.Inject

class HomeMapperImpl @Inject constructor() : HomeMapper {

    override fun toHomePageModel(homePageDto: HomePageDto): HomePage {
        return HomePage(
            homePageDto.bestSeller?.map { this.toBestSellerItemModel(it) },
            homePageDto.homeStore?.map { this.toHotItemModel(it) }
        )
    }

    override fun toBestSellerItemModel(bestSellerItemDto: BestSellerItemDto): BestSellerItem {
        return BestSellerItem(
            bestSellerItemDto.isFavorites,
            bestSellerItemDto.discountPrice,
            bestSellerItemDto.id,
            bestSellerItemDto.title,
            bestSellerItemDto.priceWithoutDiscount,
            bestSellerItemDto.picture
        )
    }

    override fun toHotItemModel(hotItemDto: HotItemDto): HotItem {
        return HotItem(
            hotItemDto.subtitle,
            hotItemDto.id,
            hotItemDto.title,
            hotItemDto.picture,
            hotItemDto.isBuy,
            hotItemDto.isNew
        )
    }
}
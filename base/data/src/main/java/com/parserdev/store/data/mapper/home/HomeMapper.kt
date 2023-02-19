package com.parserdev.store.data.mapper.home

import com.parserdev.store.data.dto.home.BestSellerItemDto
import com.parserdev.store.data.dto.home.HomePageDto
import com.parserdev.store.data.dto.home.HotItemDto
import com.parserdev.store.data.mapper.Mapper
import com.parserdev.store.domain.models.home.BestSellerItem
import com.parserdev.store.domain.models.home.HomePage
import com.parserdev.store.domain.models.home.HotItem
import javax.inject.Inject

class HomeMapper @Inject constructor() : Mapper<HomePageDto, HomePage> {

    override fun map(dto: HomePageDto): HomePage {
        return HomePage(
            dto.bestSeller?.map { this.mapBestSellerItem(it) },
            dto.homeStore?.map { this.mapHotItem(it) }
        )
    }

    private fun mapBestSellerItem(bestSellerItemDto: BestSellerItemDto): BestSellerItem {
        return BestSellerItem(
            bestSellerItemDto.isFavorites,
            bestSellerItemDto.discountPrice,
            bestSellerItemDto.id,
            bestSellerItemDto.title,
            bestSellerItemDto.priceWithoutDiscount,
            bestSellerItemDto.picture
        )
    }

    private fun mapHotItem(hotItemDto: HotItemDto): HotItem {
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
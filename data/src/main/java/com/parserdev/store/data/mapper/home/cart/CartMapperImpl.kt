package com.parserdev.store.data.mapper.home.cart

import com.parserdev.store.data.dto.home.cart.CartContentDto
import com.parserdev.store.data.dto.home.cart.CartItemDto
import com.parserdev.store.domain.models.home.cart.CartContent
import com.parserdev.store.domain.models.home.cart.CartItem
import javax.inject.Inject

class CartMapperImpl @Inject constructor() : CartMapper {
    override fun toCartContentModel(cartContentDto: CartContentDto): CartContent {
        return CartContent(
            cartContentDto.cartItemDtos?.map { this.toCartItemModel(it) },
            cartContentDto.delivery,
            cartContentDto.total,
            cartContentDto.id
        )
    }

    override fun toCartItemModel(cartItemDto: CartItemDto): CartItem {
        return CartItem(
            cartItemDto.images,
            cartItemDto.price,
            cartItemDto.id,
            cartItemDto.title,
            cartItemDto.amount
        )
    }
}
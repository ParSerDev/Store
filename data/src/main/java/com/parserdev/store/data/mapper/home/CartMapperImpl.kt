package com.parserdev.store.data.mapper.home

import com.parserdev.store.data.dto.cart.CartContentDto
import com.parserdev.store.data.dto.cart.CartItemDto
import com.parserdev.store.domain.models.cart.CartContent
import com.parserdev.store.domain.models.cart.CartItem
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
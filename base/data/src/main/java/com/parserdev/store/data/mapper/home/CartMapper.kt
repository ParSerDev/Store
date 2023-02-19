package com.parserdev.store.data.mapper.home

import com.parserdev.store.data.dto.cart.CartContentDto
import com.parserdev.store.data.dto.cart.CartItemDto
import com.parserdev.store.data.mapper.Mapper
import com.parserdev.store.domain.models.cart.CartContent
import com.parserdev.store.domain.models.cart.CartItem
import javax.inject.Inject

class CartMapper @Inject constructor() : Mapper<CartContentDto, CartContent> {
    override fun map(dto: CartContentDto): CartContent {
        return CartContent(
            dto.cartItemDtos?.map { this.mapCartItem(it) },
            dto.delivery,
            dto.total,
            dto.id
        )
    }

    private fun mapCartItem(cartItemDto: CartItemDto): CartItem {
        return CartItem(
            cartItemDto.images,
            cartItemDto.price,
            cartItemDto.id,
            cartItemDto.title,
            cartItemDto.amount
        )
    }
}
package com.parserdev.store.data.mapper.home

import com.parserdev.store.data.dto.cart.CartContentDto
import com.parserdev.store.data.dto.cart.CartItemDto
import com.parserdev.store.domain.models.cart.CartContent
import com.parserdev.store.domain.models.cart.CartItem

interface CartMapper {
    fun toCartContentModel(cartContentDto: CartContentDto): CartContent
    fun toCartItemModel(cartItemDto: CartItemDto): CartItem
}
package com.parserdev.store.data.mapper.home.cart

import com.parserdev.store.data.dto.home.cart.CartContentDto
import com.parserdev.store.data.dto.home.cart.CartItemDto
import com.parserdev.store.domain.models.home.cart.CartContent
import com.parserdev.store.domain.models.home.cart.CartItem

interface CartMapper {
    fun toCartContentModel(cartContentDto: CartContentDto): CartContent
    fun toCartItemModel(cartItemDto: CartItemDto): CartItem
}
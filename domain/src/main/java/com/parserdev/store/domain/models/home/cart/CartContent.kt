package com.parserdev.store.domain.models.home.cart

data class CartContent(

    val cartItems: List<CartItem?>? = null,

    val delivery: String? = null,

    val total: Int? = null,

    val id: String? = null
)
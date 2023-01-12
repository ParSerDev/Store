package com.parserdev.store.cart

import androidx.lifecycle.ViewModel
import com.parserdev.store.data.repository.home.CartRepository
import javax.inject.Inject

class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository
) : ViewModel() {

}


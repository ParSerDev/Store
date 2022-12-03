package com.parserdev.store.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.parserdev.store.data.repository.cart.CartRepository
import javax.inject.Inject

class CartViewModelFactory @Inject constructor(
    private val repository: CartRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CartViewModel(repository) as T
    }
}
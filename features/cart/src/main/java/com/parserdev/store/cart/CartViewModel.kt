package com.parserdev.store.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parserdev.store.data.repository.cart.CartRepository
import com.parserdev.store.domain.models.cart.CartContent
import com.parserdev.store.domain.network.NetworkResult
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _cartContent: MutableStateFlow<NetworkResult<CartContent?>> =
        MutableStateFlow(NetworkResult.Loading())
    val cartContent: StateFlow<NetworkResult<CartContent?>> = _cartContent

    init {
        viewModelScope.launch {
            _cartContent.value = cartRepository.getCartContent()
        }
    }

}


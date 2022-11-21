package com.parserdev.store.cart.di

import com.parserdev.store.cart.di.scopes.CartScope
import com.parserdev.store.cart.presentation.CartFragment
import dagger.Subcomponent

@CartScope
@Subcomponent
interface CartComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): CartComponent
    }

    fun inject(cartFragment: CartFragment)
}
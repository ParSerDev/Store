package com.parserdev.store.cart.di

import com.parserdev.store.cart.CartFragment
import com.parserdev.store.cart.di.scopes.CartScope
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
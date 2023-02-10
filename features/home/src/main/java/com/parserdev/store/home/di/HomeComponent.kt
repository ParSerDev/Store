package com.parserdev.store.home.di

import com.parserdev.store.home.di.scopes.HomeScope
import com.parserdev.store.home.presentation.CartFragment
import com.parserdev.store.home.presentation.HomeFragment
import dagger.Subcomponent

@HomeScope
@Subcomponent(
    modules =[HomeModule::class]
)
interface HomeComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }

    fun inject(homeFragment: HomeFragment)
    fun inject(cartFragment: CartFragment)
}
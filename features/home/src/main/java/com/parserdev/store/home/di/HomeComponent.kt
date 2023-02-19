package com.parserdev.store.home.di

import com.parserdev.store.data.dto.cart.CartContentDto
import com.parserdev.store.data.dto.home.HomePageDto
import com.parserdev.store.data.mapper.Mapper
import com.parserdev.store.domain.models.cart.CartContent
import com.parserdev.store.domain.models.home.HomePage
import com.parserdev.store.home.di.scopes.HomeScope
import com.parserdev.store.home.presentation.CartFragment
import com.parserdev.store.home.presentation.HomeFragment
import dagger.Subcomponent

@HomeScope
@Subcomponent
interface HomeComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }
    fun homeMapper(): Mapper<HomePageDto, HomePage>
    fun cartMapper(): Mapper<CartContentDto, CartContent>
    fun inject(homeFragment: HomeFragment)
    fun inject(cartFragment: CartFragment)
}
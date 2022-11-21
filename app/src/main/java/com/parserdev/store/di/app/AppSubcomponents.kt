package com.parserdev.store.di.app

import com.parserdev.store.cart.di.CartComponent
import com.parserdev.store.home.di.HomeComponent
import dagger.Module

@Module(
    subcomponents = [
        HomeComponent::class,
        CartComponent::class
    ]
)
class AppSubcomponents
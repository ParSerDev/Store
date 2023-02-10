package com.parserdev.store.home.di

import com.parserdev.store.data.mapper.home.HomeMapper
import com.parserdev.store.data.mapper.home.HomeMapperImpl
import com.parserdev.store.data.mapper.home.cart.CartMapper
import com.parserdev.store.data.mapper.home.cart.CartMapperImpl
import dagger.Binds
import dagger.Module

@Module
abstract class HomeModule {
    @Binds
    abstract fun provideHomeMapper(instance: HomeMapperImpl): HomeMapper
    @Binds
    abstract fun provideCartMapper(instance: CartMapperImpl): CartMapper
}
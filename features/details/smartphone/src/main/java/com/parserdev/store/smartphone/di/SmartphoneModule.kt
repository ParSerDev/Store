package com.parserdev.store.smartphone.di

import com.parserdev.store.data.mapper.details.SmartphoneDetailsMapper
import com.parserdev.store.data.mapper.details.SmartphoneDetailsMapperImpl
import com.parserdev.store.data.mapper.home.HomeMapper
import com.parserdev.store.data.mapper.home.HomeMapperImpl
import com.parserdev.store.data.mapper.home.cart.CartMapper
import com.parserdev.store.data.mapper.home.cart.CartMapperImpl
import dagger.Binds
import dagger.Module

@Module
abstract class SmartphoneModule {
    @Binds
    abstract fun provideSmartphoneDetailsMapper(instance: SmartphoneDetailsMapperImpl): SmartphoneDetailsMapper

}
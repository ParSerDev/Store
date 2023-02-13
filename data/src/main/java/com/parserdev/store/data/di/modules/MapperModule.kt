package com.parserdev.store.data.di.modules

import com.parserdev.store.data.mapper.details.SmartphoneDetailsMapper
import com.parserdev.store.data.mapper.details.SmartphoneDetailsMapperImpl
import com.parserdev.store.data.mapper.home.CartMapper
import com.parserdev.store.data.mapper.home.CartMapperImpl
import com.parserdev.store.data.mapper.home.HomeMapper
import com.parserdev.store.data.mapper.home.HomeMapperImpl
import dagger.Binds
import dagger.Module

@Module
abstract class MapperModule {
    @Binds
    abstract fun provideHomeMapper(instance: HomeMapperImpl): HomeMapper
    @Binds
    abstract fun provideCartMapper(instance: CartMapperImpl): CartMapper
    @Binds
    abstract fun provideSmartphoneDetailsMapper(instance: SmartphoneDetailsMapperImpl): SmartphoneDetailsMapper
}
package com.parserdev.store.data.di.modules

import com.parserdev.store.data.mapper.details.SmartphoneDetailsMapper
import com.parserdev.store.data.mapper.details.SmartphoneDetailsMapperImpl
import com.parserdev.store.data.mapper.home.CartMapper
import com.parserdev.store.data.mapper.home.CartMapperImpl
import com.parserdev.store.data.mapper.home.HomeMapper
import com.parserdev.store.data.mapper.home.HomeMapperImpl
import com.parserdev.store.data.repository.home.CartRepository
import com.parserdev.store.data.repository.home.CartRepositoryImpl
import com.parserdev.store.data.repository.details.DetailsRepository
import com.parserdev.store.data.repository.details.DetailsRepositoryImpl
import com.parserdev.store.data.repository.home.HomeRepository
import com.parserdev.store.data.repository.home.HomeRepositoryImpl
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
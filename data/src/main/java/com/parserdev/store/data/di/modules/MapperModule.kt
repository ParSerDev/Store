package com.parserdev.store.data.di.modules

import com.parserdev.store.data.mapper.details.SmartphoneDetailsMapper
import com.parserdev.store.data.mapper.details.SmartphoneDetailsMapperImpl
import dagger.Binds
import dagger.Module

@Module
abstract class MapperModule {
    @Binds
    abstract fun provideSmartphoneDetailsMapper(instance: SmartphoneDetailsMapperImpl): SmartphoneDetailsMapper
}
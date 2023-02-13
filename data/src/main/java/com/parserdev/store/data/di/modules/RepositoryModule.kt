package com.parserdev.store.data.di.modules

import com.parserdev.store.data.repository.home.CartRepository
import com.parserdev.store.data.repository.home.CartRepositoryImpl
import com.parserdev.store.data.repository.details.DetailsRepository
import com.parserdev.store.data.repository.details.DetailsRepositoryImpl
import com.parserdev.store.data.repository.home.HomeRepository
import com.parserdev.store.data.repository.home.HomeRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun provideHomeRepository(instance: HomeRepositoryImpl): HomeRepository
    @Binds
    abstract fun provideDetailsRepository(instance: DetailsRepositoryImpl): DetailsRepository
    @Binds
    abstract fun provideCartRepository(instance: CartRepositoryImpl): CartRepository
}
package com.parserdev.store.data.di.modules

import com.parserdev.store.data.network.NetworkInstance
import com.parserdev.store.data.network.retrofit.RetrofitInstance
import dagger.Binds
import dagger.Module

@Module
abstract class NetworkModule {
    @Binds
    abstract fun provideNetworkInstance(instance: RetrofitInstance): NetworkInstance
}
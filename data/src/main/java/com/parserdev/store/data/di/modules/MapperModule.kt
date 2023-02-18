package com.parserdev.store.data.di.modules

import com.parserdev.store.data.dto.cart.CartContentDto
import com.parserdev.store.data.dto.details.SmartphoneDetailsDto
import com.parserdev.store.data.dto.home.HomePageDto
import com.parserdev.store.data.mapper.Mapper
import com.parserdev.store.data.mapper.details.SmartphoneDetailsMapper
import com.parserdev.store.data.mapper.home.CartMapper
import com.parserdev.store.data.mapper.home.HomeMapper
import com.parserdev.store.domain.models.cart.CartContent
import com.parserdev.store.domain.models.details.SmartphoneDetails
import com.parserdev.store.domain.models.home.HomePage
import dagger.Binds
import dagger.Module

@Module
abstract class MapperModule {
    @Binds
    abstract fun provideHomeMapper(instance: HomeMapper): Mapper<HomePageDto, HomePage>

    @Binds
    abstract fun provideCartMapper(instance: CartMapper): Mapper<CartContentDto, CartContent>

    @Binds
    abstract fun provideSmartphoneDetailsMapper(instance: SmartphoneDetailsMapper): Mapper<SmartphoneDetailsDto, SmartphoneDetails>

}
package com.parserdev.store.di.component

import android.content.Context
import com.parserdev.store.data.di.modules.MapperModule
import com.parserdev.store.data.di.modules.NetworkModule
import com.parserdev.store.data.di.modules.RepositoryModule
import com.parserdev.store.data.dto.cart.CartContentDto
import com.parserdev.store.data.dto.details.SmartphoneDetailsDto
import com.parserdev.store.data.dto.home.HomePageDto
import com.parserdev.store.data.mapper.Mapper
import com.parserdev.store.di.app.AppSubcomponents
import com.parserdev.store.domain.models.cart.CartContent
import com.parserdev.store.domain.models.details.SmartphoneDetails
import com.parserdev.store.domain.models.home.HomePage
import com.parserdev.store.home.di.HomeComponent
import com.parserdev.store.smartphone.di.SmartphoneComponent
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(
    modules = [AppSubcomponents::class,
        NetworkModule::class,
        RepositoryModule::class,
        MapperModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
    fun homeMapper(): Mapper<HomePageDto, HomePage>
    fun cartMapper(): Mapper<CartContentDto, CartContent>
    fun smartphoneDetailsMapper(): Mapper<SmartphoneDetailsDto, SmartphoneDetails>
    fun homeComponent(): HomeComponent.Factory
    fun smartphoneComponent(): SmartphoneComponent.Factory
}
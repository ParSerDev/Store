package com.parserdev.store.di.component

import android.content.Context
import com.parserdev.store.cart.di.CartComponent
import com.parserdev.store.data.di.modules.DatabaseModule
import com.parserdev.store.data.di.modules.NetworkModule
import com.parserdev.store.di.app.AppSubcomponents
import com.parserdev.store.home.di.HomeComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class, DatabaseModule::class,
        AppSubcomponents::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun homeComponent(): HomeComponent.Factory
    fun cartComponent(): CartComponent.Factory
}
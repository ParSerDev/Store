package com.parserdev.store.di.component

import android.content.Context
import com.parserdev.store.data.di.modules.MapperModule
import com.parserdev.store.data.di.modules.NetworkModule
import com.parserdev.store.data.di.modules.RepositoryModule
import com.parserdev.store.di.app.AppSubcomponents
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

    fun homeComponent(): HomeComponent.Factory
    fun smartphoneComponent(): SmartphoneComponent.Factory
}
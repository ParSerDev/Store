package com.parserdev.store.di.app

import android.app.Application
import com.parserdev.store.di.component.AppComponent
import com.parserdev.store.di.component.DaggerAppComponent
import com.parserdev.store.home.di.HomeComponent
import com.parserdev.store.home.di.HomeComponentProvider
import com.parserdev.store.smartphone.di.SmartphoneComponent
import com.parserdev.store.smartphone.di.SmartphoneComponentProvider

open class App : Application(), HomeComponentProvider,
    SmartphoneComponentProvider {
    private val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }

    override fun provideHomeComponent(): HomeComponent {
        return appComponent.homeComponent().create()
    }

    override fun provideSmartphoneComponent(): SmartphoneComponent {
        return appComponent.smartphoneComponent().create()
    }

}
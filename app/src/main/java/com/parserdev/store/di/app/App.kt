package com.parserdev.store.di.app

import android.app.Application
import com.parserdev.store.cart.di.CartComponent
import com.parserdev.store.cart.di.CartComponentProvider
import com.parserdev.store.di.component.AppComponent
import com.parserdev.store.di.component.DaggerAppComponent
import com.parserdev.store.home.di.HomeComponent
import com.parserdev.store.home.di.HomeComponentProvider

open class App : Application(), HomeComponentProvider,
    CartComponentProvider {
    private val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }

    override fun provideHomeComponent(): HomeComponent {
        return appComponent.homeComponent().create()
    }

    override fun provideCartComponent(): CartComponent {
        return appComponent.cartComponent().create()
    }
}
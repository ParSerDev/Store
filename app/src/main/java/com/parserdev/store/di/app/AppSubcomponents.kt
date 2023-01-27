package com.parserdev.store.di.app

import com.parserdev.store.home.di.HomeComponent
import com.parserdev.store.smartphone.di.SmartphoneComponent
import dagger.Module

@Module(
    subcomponents = [
        HomeComponent::class,
        SmartphoneComponent::class
    ]
)
class AppSubcomponents
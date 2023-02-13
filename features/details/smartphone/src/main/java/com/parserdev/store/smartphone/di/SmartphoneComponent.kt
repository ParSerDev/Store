package com.parserdev.store.smartphone.di

import com.parserdev.store.smartphone.di.scopes.SmartphoneScope
import com.parserdev.store.smartphone.presentation.SmartphoneFragment
import dagger.Subcomponent

@SmartphoneScope
@Subcomponent
interface SmartphoneComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): SmartphoneComponent
    }
    fun inject(smartphoneFragment: SmartphoneFragment)
}
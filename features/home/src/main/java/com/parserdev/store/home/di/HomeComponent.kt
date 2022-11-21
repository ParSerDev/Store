package com.parserdev.store.home.di

import com.parserdev.store.home.di.scopes.HomeScope
import com.parserdev.store.home.presentation.details.DetailsFragment
import com.parserdev.store.home.presentation.home.HomeFragment
import dagger.Subcomponent

@HomeScope
@Subcomponent
interface HomeComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }

    fun inject(homeFragment: HomeFragment)
    fun inject(detailsFragment: DetailsFragment)
}
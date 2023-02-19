package com.parserdev.store.smartphone.di

import com.parserdev.store.data.dto.details.SmartphoneDetailsDto
import com.parserdev.store.data.mapper.Mapper
import com.parserdev.store.domain.models.details.SmartphoneDetails
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
    fun smartphoneDetailsMapper(): Mapper<SmartphoneDetailsDto, SmartphoneDetails>
    fun inject(smartphoneFragment: SmartphoneFragment)
}
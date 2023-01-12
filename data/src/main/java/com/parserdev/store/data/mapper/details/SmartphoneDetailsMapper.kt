package com.parserdev.store.data.mapper.details

import com.parserdev.store.data.dto.details.SmartphoneDetailsDto
import com.parserdev.store.domain.models.details.SmartphoneDetails

interface SmartphoneDetailsMapper {
    fun toSmartphoneDetailsModel(smartphoneDetailsDto: SmartphoneDetailsDto): SmartphoneDetails
}
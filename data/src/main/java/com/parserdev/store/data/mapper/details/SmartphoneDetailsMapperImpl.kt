package com.parserdev.store.data.mapper.details

import com.parserdev.store.data.dto.details.SmartphoneDetailsDto
import com.parserdev.store.domain.models.details.SmartphoneDetails
import javax.inject.Inject

class SmartphoneDetailsMapperImpl @Inject constructor() : SmartphoneDetailsMapper {
    override fun toSmartphoneDetailsModel(smartphoneDetailsDto: SmartphoneDetailsDto): SmartphoneDetails {
        return SmartphoneDetails(
            smartphoneDetailsDto.sd,
            smartphoneDetailsDto.images,
            smartphoneDetailsDto.color,
            smartphoneDetailsDto.ssd,
            smartphoneDetailsDto.price,
            smartphoneDetailsDto.rating,
            smartphoneDetailsDto.cPU,
            smartphoneDetailsDto.isFavorites,
            smartphoneDetailsDto.id,
            smartphoneDetailsDto.camera,
            smartphoneDetailsDto.title,
            smartphoneDetailsDto.capacity
        )
    }
}
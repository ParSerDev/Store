package com.parserdev.store.data.mapper.details

import com.parserdev.store.data.dto.details.SmartphoneDetailsDto
import com.parserdev.store.data.mapper.Mapper
import com.parserdev.store.domain.models.details.SmartphoneDetails
import javax.inject.Inject

class SmartphoneDetailsMapper @Inject constructor() : Mapper<SmartphoneDetailsDto, SmartphoneDetails> {
    override fun map(dto: SmartphoneDetailsDto): SmartphoneDetails {
        return SmartphoneDetails(
            dto.sd,
            dto.images,
            dto.color,
            dto.ssd,
            dto.price,
            dto.rating,
            dto.cPU,
            dto.isFavorites,
            dto.id,
            dto.camera,
            dto.title,
            dto.capacity
        )
    }
}
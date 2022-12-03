package com.parserdev.store.smartphone.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parserdev.store.data.dto.details.SmartphonePurchaseDto
import com.parserdev.store.data.repository.details.DetailsRepository
import com.parserdev.store.domain.models.details.PhoneDetails
import com.parserdev.store.domain.models.home.HomeCategory
import com.parserdev.store.domain.network.NetworkResult
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class SmartphoneViewModel @Inject constructor(
    private val detailsRepository: DetailsRepository
) : ViewModel() {

    var purchase = SmartphonePurchaseDto()
    val accept: (SmartphoneAction) -> Unit
    private val _smartphoneDetails: MutableStateFlow<NetworkResult<PhoneDetails?>> =
        MutableStateFlow(NetworkResult.Loading())
    val smartphoneDetails: StateFlow<NetworkResult<PhoneDetails?>> = _smartphoneDetails

    init {
        viewModelScope.launch {
            _smartphoneDetails.value = detailsRepository.getPhoneDetails(3)
            if (smartphoneDetails.value is NetworkResult.Success) purchase = SmartphonePurchaseDto(
                id = smartphoneDetails.value.data?.id ?: "-1",
                color = smartphoneDetails.value.data?.color?.get(0) ?: "#FFFFFF",
                capacity = smartphoneDetails.value.data?.capacity?.get(0) ?: ""
            )
        }

        accept = { action ->
            viewModelScope.launch {
                when (action) {
                    is SmartphoneAction.UpdatePurchaseColor -> {
                        purchase.color = action.color
                    }
                    is SmartphoneAction.UpdatePurchaseCapacity -> {
                        purchase.capacity = action.capacity
                    }
                    is SmartphoneAction.AddToFavourites -> {}
                    is SmartphoneAction.RemoveFromFavourites -> {}
                    is SmartphoneAction.AddToCart -> {}
                }
            }
        }

    }

}

sealed class SmartphoneAction {
    data class UpdatePurchaseColor(val color: String) : SmartphoneAction()
    data class UpdatePurchaseCapacity(val capacity: String) : SmartphoneAction()
    data class AddToCart(val purchase: SmartphonePurchaseDto) : SmartphoneAction()
    data class AddToFavourites(val id: Int) : SmartphoneAction()
    data class RemoveFromFavourites(val id: Int) : SmartphoneAction()
}


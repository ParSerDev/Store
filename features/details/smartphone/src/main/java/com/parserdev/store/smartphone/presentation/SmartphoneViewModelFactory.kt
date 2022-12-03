package com.parserdev.store.smartphone.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.parserdev.store.data.repository.details.DetailsRepository
import javax.inject.Inject

class SmartphoneViewModelFactory @Inject constructor(
    private val repository: DetailsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SmartphoneViewModel(repository) as T
    }
}
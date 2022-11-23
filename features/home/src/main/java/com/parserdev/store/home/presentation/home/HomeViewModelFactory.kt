package com.parserdev.store.home.presentation.home

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.parserdev.store.data.repository.home.HomeRepository
import com.parserdev.store.data.repository.home.HomeRepositoryImpl
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class HomeViewModelFactory @AssistedInject constructor(
    private val repository: HomeRepositoryImpl,
    @Assisted owner: SavedStateRegistryOwner
) : AbstractSavedStateViewModelFactory(owner, null) {
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T = HomeViewModel(repository, handle) as T
}

@AssistedFactory
interface HomeViewModelAssistedFactory {
    fun create(owner: SavedStateRegistryOwner): HomeViewModelFactory
}
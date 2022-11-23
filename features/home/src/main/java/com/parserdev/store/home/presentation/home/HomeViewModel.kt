package com.parserdev.store.home.presentation.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parserdev.store.data.repository.home.HomeRepository
import com.parserdev.store.domain.models.home.HomeCategory
import com.parserdev.store.domain.models.home.HomePage
import com.parserdev.store.domain.network.NetworkResult
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel @AssistedInject constructor(
    private val repository: HomeRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state: StateFlow<HomeState>
    var homePage: Flow<NetworkResult<HomePage?>>
    val accept: (HomeAction) -> Unit


    init {
        val initialCategory: HomeCategory =
            savedStateHandle[LAST_CATEGORY] ?: HomeCategory.PHONES
        val initialQuery: String = savedStateHandle[LAST_SEARCH_QUERY] ?: DEFAULT_QUERY
        val actionStateFlow = MutableSharedFlow<HomeAction>()
        val changedCategory = actionStateFlow
            .filterIsInstance<HomeAction.ChangeCategory>()
            .distinctUntilChanged()
            .onStart { emit(HomeAction.ChangeCategory(category = initialCategory)) }
        val searches = actionStateFlow
            .filterIsInstance<HomeAction.Search>()
            .distinctUntilChanged()
            .onStart { emit(HomeAction.Search(query = initialQuery)) }
        state = combine(
            searches,
            changedCategory
        ) { search, category ->
            HomeState(
                query = search.query,
                category = category.category
            )
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
                initialValue = HomeState()
            )
        accept = { action ->
            viewModelScope.launch { actionStateFlow.emit(action) }
        }

            homePage = state.flatMapLatest { homeState ->
                repository.getHomePage(homeCategory = homeState.category)
            }
    }

    override fun onCleared() {
        savedStateHandle[LAST_SEARCH_QUERY] = state.value.query
        savedStateHandle[LAST_CATEGORY] = state.value.category
        super.onCleared()
    }

}


sealed class HomeAction {
    data class Search(val query: String) : HomeAction()
    data class ChangeCategory(val category: HomeCategory) : HomeAction()
}

data class HomeState(
    val query: String = "",
    val category: HomeCategory = HomeCategory.PHONES
)

private const val LAST_SEARCH_QUERY: String = "last_search_query"
private const val LAST_CATEGORY: String = "last_category"
private const val DEFAULT_QUERY: String = ""
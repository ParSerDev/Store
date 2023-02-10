package com.parserdev.store.home.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parserdev.store.data.repository.home.cart.CartRepository
import com.parserdev.store.data.repository.home.HomeRepository
import com.parserdev.store.domain.models.home.cart.CartContent
import com.parserdev.store.domain.models.home.HomeCategory
import com.parserdev.store.domain.models.home.HomePage
import com.parserdev.store.domain.network.NetworkResult
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel @AssistedInject constructor(
    private val homeRepository: HomeRepository,
    private val cartRepository: CartRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state: StateFlow<HomeState>
    var homePage: Flow<NetworkResult<HomePage?>>
    var cartContent: StateFlow<NetworkResult<CartContent?>> = MutableStateFlow(NetworkResult.Loading())
    private val accept: (HomeAction) -> Unit

    val locationsList = listOf(
        Location.Gro.name, Location.Moscow.name
    )
    val brandsList = listOf(
        HomeFilter.Brand.Samsung.name,
        HomeFilter.Brand.Sony.name,
        HomeFilter.Brand.IPhone.name
    )
    val pricesList = listOf(
        HomeFilter.Price.Low.amount,
        HomeFilter.Price.Average.amount
    )
    val sizesList = listOf(
        HomeFilter.Size.Small.inches,
        HomeFilter.Size.Medium.inches
    )

    init {
        val initialCategory: HomeCategory = savedStateHandle[LAST_CATEGORY] ?: HomeCategory.PHONES
        val initialQuery: String = savedStateHandle[DEFAULT_QUERY] ?: DEFAULT_QUERY
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
                started = SharingStarted.Lazily,
                initialValue = HomeState()
            )
        accept = { action ->
            viewModelScope.launch { actionStateFlow.emit(action) }
        }
        homePage = state.flatMapLatest { homeState ->
            homeRepository.getHomePage(homeCategory = homeState.category)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = NetworkResult.Loading()
        )
        viewModelScope.launch {
            cartContent = cartRepository.getCartContent().stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = NetworkResult.Loading()
            )
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
    val category: HomeCategory = HomeCategory.PHONES,
    val filter: HomeFilter = HomeFilter(),
    val location: Location = Location.Gro
)

data class HomeFilter(
    val brand: Brand = Brand.Samsung,
    val price: Price = Price.Low,
    val size: Size = Size.Small
) {
    sealed class Brand(val name: String) {
        object Samsung : Brand(name = "Samsung")
        object Sony : Brand(name = "Sony")
        object IPhone : Brand(name = "IPhone")
    }

    sealed class Price(val amount: String) {
        object Low : Price(amount = "$300 - $500")
        object Average : Price(amount = "$500 - $1,000")
    }

    sealed class Size(val inches: String) {
        object Small : Size(inches = "4.5 to 5.5 inches")
        object Medium : Size(inches = "5.5 to 6.5 inches")
    }
}

sealed class Location(val name: String) {
    object Gro : Location(name = "Zihuatanejo, Gro")
    object Moscow : Location(name = "Russia, Moscow")
}

private const val LAST_SEARCH_QUERY: String = "last_search_query"
private const val LAST_CATEGORY: String = "last_category"
private const val DEFAULT_QUERY: String = ""
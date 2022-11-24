package com.parserdev.store.home.presentation.home

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.parserdev.store.domain.models.home.CategoryItem
import com.parserdev.store.domain.models.home.HomeCategory
import com.parserdev.store.domain.network.NetworkResult
import com.parserdev.ui_components.R
import com.parserdev.store.home.databinding.FragmentHomeBinding
import com.parserdev.store.home.di.HomeComponentProvider
import com.parserdev.store.home.presentation.getColorFromAttr
import com.parserdev.store.home.presentation.home.adapters.*
import com.parserdev.store.home.presentation.home.adapters.delegate.CompositeAdapter
import com.parserdev.store.home.presentation.home.adapters.model.BestSellersListItem
import com.parserdev.store.home.presentation.home.adapters.model.HotSalesListItem
import com.parserdev.store.home.presentation.home.adapters.model.SearchFieldItem
import com.parserdev.store.home.presentation.home.adapters.model.SelectCategoryListItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var homeAssistedFactory: HomeViewModelAssistedFactory
    private lateinit var homeViewModel: HomeViewModel

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var compositeAdapter: CompositeAdapter
    private lateinit var categories: List<CategoryItem>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectHomeComponent()
        provideViewModel()
        binding.bindState()
    }

    private fun FragmentHomeBinding.bindState() {
        bindSpinner()
        bindRecyclerView()
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                homeViewModel.accept(
                    HomeAction.ChangeCategory(
                        category = HomeCategory.PHONES
                    )
                )
            }
        }
    }

    private fun FragmentHomeBinding.bindSpinner() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.locations_array,
            R.layout.spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    private fun FragmentHomeBinding.bindRecyclerView() {
        initCompositeAdapter()
        initCategories()
        recyclerView.adapter = compositeAdapter
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                homeViewModel.homePage.collect { page ->
                    when (page) {
                        is NetworkResult.Success -> {
                            compositeAdapter.submitList(
                                listOf(
                                    SelectCategoryListItem(
                                        categories = categories
                                    ),
                                    SearchFieldItem(),
                                    HotSalesListItem(
                                        items = page.data?.hotItems
                                    ),
                                    BestSellersListItem(
                                        bestSellers = page.data?.bestSellers
                                    )
                                )
                            )
                        }
                        else -> {}
                    }
                }
            }
        }
    }

    private fun initCompositeAdapter() {
        val selectCategoryDelegateAdapter = SelectCategoryDelegateAdapter(
            clickListener = {},
            marginRight = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                27F,
                resources.displayMetrics
            ).toInt(),
            marginLeft = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                27F,
                resources.displayMetrics
            ).toInt()
        )
        val searchFieldDelegateAdapter = SearchFieldDelegateAdapter(editTextListener = {})
        val hotSalesDelegateAdapter = HotSalesDelegateAdapter(clickListener = {})
        val bestSellerDelegateAdapter = BestSellerDelegateAdapter(
            likeClickListener = {},
            navigationClickListener = {},
            gridLayoutManager = GridLayoutManager(requireContext(), 2),
            marginLeft = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                17F,
                resources.displayMetrics
            ).toInt(),
            marginRight = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                21F,
                resources.displayMetrics
            ).toInt()
        )
        compositeAdapter = CompositeAdapter.Builder()
            .add(selectCategoryDelegateAdapter)
            .add(searchFieldDelegateAdapter)
            .add(hotSalesDelegateAdapter)
            .add(bestSellerDelegateAdapter)
            .build()
    }

    private fun initCategories() {
        categories = listOf(
            CategoryItem(
                name = resources.getString(R.string.phones),
                iconActiveId = R.drawable.ic_phone_active,
                iconInactiveId = R.drawable.ic_phone_inactive,
                backgroundActiveId = R.drawable.ripple_onprimary_shape_primary_rounded50dp,
                backgroundInactiveId = R.drawable.ripple_primary_shape_onprimary_rounded50dp,
                textColorActiveId = requireContext().getColorFromAttr(android.R.attr.textColorPrimary),
                textColorInactiveId = requireContext().getColorFromAttr(android.R.attr.textColorSecondary),
                homeCategory = HomeCategory.PHONES
            ),
            CategoryItem(
                name = resources.getString(R.string.computer),
                iconActiveId = R.drawable.ic_computer_active,
                iconInactiveId = R.drawable.ic_computer_inactive,
                backgroundActiveId = R.drawable.ripple_onprimary_shape_primary_rounded50dp,
                backgroundInactiveId = R.drawable.ripple_primary_shape_onprimary_rounded50dp,
                textColorActiveId = requireContext().getColorFromAttr(android.R.attr.textColorPrimary),
                textColorInactiveId = requireContext().getColorFromAttr(android.R.attr.textColorSecondary),
                homeCategory = HomeCategory.COMPUTERS
            ),
            CategoryItem(
                name = resources.getString(R.string.health),
                iconActiveId = R.drawable.ic_health_active,
                iconInactiveId = R.drawable.ic_health_inactive,
                backgroundActiveId = R.drawable.ripple_onprimary_shape_primary_rounded50dp,
                backgroundInactiveId = R.drawable.ripple_primary_shape_onprimary_rounded50dp,
                textColorActiveId = requireContext().getColorFromAttr(android.R.attr.textColorPrimary),
                textColorInactiveId = requireContext().getColorFromAttr(android.R.attr.textColorSecondary),
                homeCategory = HomeCategory.HEALTH
            ),
            CategoryItem(
                name = resources.getString(R.string.books),
                iconActiveId = R.drawable.ic_books_active,
                iconInactiveId = R.drawable.ic_books_inactive,
                backgroundActiveId = R.drawable.ripple_onprimary_shape_primary_rounded50dp,
                backgroundInactiveId = R.drawable.ripple_primary_shape_onprimary_rounded50dp,
                textColorActiveId = requireContext().getColorFromAttr(android.R.attr.textColorPrimary),
                textColorInactiveId = requireContext().getColorFromAttr(android.R.attr.textColorSecondary),
                homeCategory = HomeCategory.BOOKS
            ),
            CategoryItem(
                name = resources.getString(R.string.tools),
                iconActiveId = R.drawable.ic_tool_active,
                iconInactiveId = R.drawable.ic_tool_inactive,
                backgroundActiveId = R.drawable.ripple_onprimary_shape_primary_rounded50dp,
                backgroundInactiveId = R.drawable.ripple_primary_shape_onprimary_rounded50dp,
                textColorActiveId = requireContext().getColorFromAttr(android.R.attr.textColorPrimary),
                textColorInactiveId = requireContext().getColorFromAttr(android.R.attr.textColorSecondary),
                homeCategory = HomeCategory.TOOLS
            )
        )
    }

    private fun injectHomeComponent() {
        val homeComponent =
            (activity?.application as HomeComponentProvider).provideHomeComponent()
        homeComponent.inject(this)
    }

    private fun provideViewModel() {
        val viewModelFactory = homeAssistedFactory.create(this)
        homeViewModel =
            ViewModelProvider(requireActivity(), viewModelFactory)[HomeViewModel::class.java]
    }
}
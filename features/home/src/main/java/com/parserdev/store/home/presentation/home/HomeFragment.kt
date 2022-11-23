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
import com.parserdev.store.domain.models.home.Category
import com.parserdev.store.domain.models.home.HomeCategory
import com.parserdev.store.domain.network.NetworkResult
import com.parserdev.ui_components.R
import com.parserdev.store.home.databinding.FragmentHomeBinding
import com.parserdev.store.home.di.HomeComponentProvider
import com.parserdev.store.home.presentation.getColorFromAttr
import com.parserdev.store.home.presentation.home.adapters.HotSalesDelegateAdapter
import com.parserdev.store.home.presentation.home.adapters.SearchFieldDelegateAdapter
import com.parserdev.store.home.presentation.home.adapters.SelectCategoryDelegateAdapter
import com.parserdev.store.home.presentation.home.adapters.delegate.CompositeAdapter
import com.parserdev.store.home.presentation.home.adapters.model.HotSalesItem
import com.parserdev.store.home.presentation.home.adapters.model.SearchFieldItem
import com.parserdev.store.home.presentation.home.adapters.model.SelectCategoryItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var homeAssistedFactory: HomeViewModelAssistedFactory
    private lateinit var homeViewModel: HomeViewModel

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var compositeAdapter: CompositeAdapter
    private lateinit var categories: List<Category>

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
                                    SelectCategoryItem(
                                        categories = categories
                                    ),
                                    SearchFieldItem(),
                                    HotSalesItem(
                                        items = page.data?.hotItems
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
        val marginLeft = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            27F,
            resources.displayMetrics
        ).toInt()
        val marginRight = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            27F,
            resources.displayMetrics
        ).toInt()
        val selectCategoryDelegateAdapter = SelectCategoryDelegateAdapter(
            clickListener = {},
            marginRight = marginRight,
            marginLeft = marginLeft
        )
        val searchFieldDelegateAdapter = SearchFieldDelegateAdapter(editTextListener = {})
        val hotSalesDelegateAdapter = HotSalesDelegateAdapter(clickListener = {})
        compositeAdapter = CompositeAdapter.Builder()
            .add(selectCategoryDelegateAdapter)
            .add(searchFieldDelegateAdapter)
            .add(hotSalesDelegateAdapter)
            .build()
    }

    private fun initCategories() {
        categories = listOf(
            Category(
                name = resources.getString(R.string.phones),
                iconActiveId = R.drawable.ic_phone_active,
                iconInactiveId = R.drawable.ic_phone_inactive,
                backgroundActiveId = R.drawable.shape_rounded50dp_primary,
                backgroundInactiveId = R.drawable.shape_rounded50dp_onprimary,
                textColorActiveId = requireContext().getColorFromAttr(android.R.attr.textColorPrimary),
                textColorInactiveId = requireContext().getColorFromAttr(android.R.attr.textColorSecondary),
                homeCategory = HomeCategory.PHONES
            ),
            Category(
                name = resources.getString(R.string.computer),
                iconActiveId = R.drawable.ic_computer_active,
                iconInactiveId = R.drawable.ic_computer_inactive,
                backgroundActiveId = R.drawable.shape_rounded50dp_primary,
                backgroundInactiveId = R.drawable.shape_rounded50dp_onprimary,
                textColorActiveId = requireContext().getColorFromAttr(android.R.attr.textColorPrimary),
                textColorInactiveId = requireContext().getColorFromAttr(android.R.attr.textColorSecondary),
                homeCategory = HomeCategory.COMPUTERS
            ),
            Category(
                name = resources.getString(R.string.health),
                iconActiveId = R.drawable.ic_health_active,
                iconInactiveId = R.drawable.ic_health_inactive,
                backgroundActiveId = R.drawable.shape_rounded50dp_primary,
                backgroundInactiveId = R.drawable.shape_rounded50dp_onprimary,
                textColorActiveId = requireContext().getColorFromAttr(android.R.attr.textColorPrimary),
                textColorInactiveId = requireContext().getColorFromAttr(android.R.attr.textColorSecondary),
                homeCategory = HomeCategory.HEALTH
            ),
            Category(
                name = resources.getString(R.string.books),
                iconActiveId = R.drawable.ic_books_active,
                iconInactiveId = R.drawable.ic_books_inactive,
                backgroundActiveId = R.drawable.shape_rounded50dp_primary,
                backgroundInactiveId = R.drawable.shape_rounded50dp_onprimary,
                textColorActiveId = requireContext().getColorFromAttr(android.R.attr.textColorPrimary),
                textColorInactiveId = requireContext().getColorFromAttr(android.R.attr.textColorSecondary),
                homeCategory = HomeCategory.BOOKS
            ),
            Category(
                name = resources.getString(R.string.tools),
                iconActiveId = R.drawable.ic_tool_active,
                iconInactiveId = R.drawable.ic_tool_inactive,
                backgroundActiveId = R.drawable.shape_rounded50dp_primary,
                backgroundInactiveId = R.drawable.shape_rounded50dp_onprimary,
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
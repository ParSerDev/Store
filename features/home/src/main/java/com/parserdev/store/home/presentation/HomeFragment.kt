package com.parserdev.store.home.presentation

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy
import com.parserdev.store.domain.models.home.BestSellerItem
import com.parserdev.store.domain.models.home.CategoryItem
import com.parserdev.store.domain.models.home.HomeCategory
import com.parserdev.store.domain.models.home.HotItem
import com.parserdev.store.domain.network.NetworkResult
import com.parserdev.store.home.databinding.FragmentHomeBinding
import com.parserdev.store.home.di.HomeComponentProvider
import com.parserdev.store.home.presentation.adapters.*
import com.parserdev.store.home.presentation.adapters.delegate.CompositeAdapter
import com.parserdev.store.home.presentation.adapters.model.BestSellersListItem
import com.parserdev.store.home.presentation.adapters.model.HotSalesListItem
import com.parserdev.store.home.presentation.adapters.model.SearchFieldItem
import com.parserdev.store.home.presentation.adapters.model.SelectCategoryListItem
import com.parserdev.store.utility.CONST_DEEPLINK_CART
import com.parserdev.store.utility.CONST_DEEPLINK_SMARTPHONE
import com.parserdev.store.utility.getColorFromAttr
import com.parserdev.store.utility.getDp
import com.parserdev.ui_components.R
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var homeAssistedViewModelFactory: HomeViewModelAssistedFactory
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
        initCategories()
        binding.bindState()
    }

    private fun FragmentHomeBinding.bindState() {
        bindSpinners()
        bindBottomBar()
        bindFilter()
        viewLifecycleOwner.lifecycleScope.launch {
            homeViewModel.homePage.collect { networkResult ->
                when (networkResult) {
                    is NetworkResult.Success -> {
                        val data = networkResult.data
                        bindRecyclerView(
                            hotItems = data?.hotItems,
                            bestSellers = data?.bestSellers
                        )
                        progressBar.visibility = View.GONE
                        layout.visibility = View.VISIBLE
                    }
                    is NetworkResult.Loading -> {
                        progressBar.visibility = View.VISIBLE
                        layout.visibility = View.GONE
                    }
                    is NetworkResult.Error -> {}
                }
            }
        }
    }

    private fun FragmentHomeBinding.bindFilter() {
        buttonFilter.setOnClickListener {
            ObjectAnimator.ofFloat(
                filter.card, "translationY",
                resources.getDp(pixels = -375F)
            ).apply {
                duration = 1000
                start()
            }
        }
        filter.card.setOnClickListener { }
        filter.buttonClose.setOnClickListener {
            ObjectAnimator.ofFloat(
                filter.card, "translationY",
                resources.getDp(pixels = 375F)
            ).apply {
                duration = 1000
                start()
            }
        }
    }

    private fun FragmentHomeBinding.bindBottomBar() {
        bottomBar.layoutBottomBar.setOnClickListener { }
        bottomBar.buttonCart.setOnClickListener {
            val deeplink =
                NavDeepLinkRequest.Builder.fromUri(CONST_DEEPLINK_CART.toUri()).build()
            findNavController().navigate(deeplink)
        }
    }


    private fun FragmentHomeBinding.bindSpinners() {
        ArrayAdapter(
            requireContext(),
            R.layout.spinner_item_location,
            homeViewModel.locationsList
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_dropdown_location)
            spinnerLocation.adapter = adapter
        }
        ArrayAdapter(
            requireContext(),
            R.layout.spinner_item_filter,
            homeViewModel.brandsList
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_dropdown_filter)
            filter.spinnerBrand.adapter = adapter
        }
        ArrayAdapter(
            requireContext(),
            R.layout.spinner_item_filter,
            homeViewModel.pricesList
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_dropdown_filter)
            filter.spinnerPrice.adapter = adapter
        }
        ArrayAdapter(
            requireContext(),
            R.layout.spinner_item_filter,
            homeViewModel.sizesList
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_dropdown_filter)
            filter.spinnerSize.adapter = adapter
        }

    }

    private fun FragmentHomeBinding.bindRecyclerView(
        hotItems: List<HotItem>?,
        bestSellers: List<BestSellerItem>?
    ) {
        initCompositeAdapter()

        recyclerView.adapter = compositeAdapter
        recyclerView.adapter?.stateRestorationPolicy = StateRestorationPolicy.PREVENT_WHEN_EMPTY
        compositeAdapter.submitList(
            listOf(
                SelectCategoryListItem(
                    categories = categories,
                    clickListener = {}
                ),
                SearchFieldItem(
                    editTextListener = {}
                ),
                HotSalesListItem(
                    items = hotItems,
                    clickListener = {}
                ),
                BestSellersListItem(
                    bestSellers = bestSellers,
                    likeClickListener = { pos ->
                        val item = bestSellers?.get(pos)
                        item?.isFavorites = !(item?.isFavorites ?: false)
                        val bestSellerDelegateViewHolder =
                            recyclerView.findViewHolderForAdapterPosition(3) as BestSellerDelegateAdapter.BestSellerDelegateViewHolder
                        val bestSellerDelegateRecyclerView: RecyclerView =
                            bestSellerDelegateViewHolder.binding.recyclerView
                        bestSellerDelegateRecyclerView.adapter?.notifyItemChanged(pos, item)
                    },
                    navigationClickListener = { url ->
                        val deeplink =
                            NavDeepLinkRequest.Builder.fromUri(CONST_DEEPLINK_SMARTPHONE.toUri())
                                .build()
                        findNavController().navigate(deeplink)

                    }
                )
            )
        )
    }

    private fun initCompositeAdapter() {
        val selectCategoryDelegateAdapter = SelectCategoryDelegateAdapter(
            marginRight = resources.getDp(pixels = 27F).toInt(),
            marginLeft = resources.getDp(pixels = 27F).toInt()
        )
        val searchFieldDelegateAdapter = SearchFieldDelegateAdapter()
        val hotSalesDelegateAdapter = HotSalesDelegateAdapter()
        val bestSellerDelegateAdapter = BestSellerDelegateAdapter(
            gridLayoutManager = GridLayoutManager(requireContext(), 2),
            marginLeft = resources.getDp(pixels = 17F).toInt(),
            marginRight = resources.getDp(pixels = 21F).toInt()
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
                backgroundActiveId = requireContext().getColorFromAttr(android.R.attr.colorPrimary),
                backgroundInactiveId = requireContext().getColorFromAttr(android.R.attr.textColor),
                textColorActiveId = requireContext().getColorFromAttr(android.R.attr.colorPrimary),
                textColorInactiveId = requireContext().getColorFromAttr(android.R.attr.colorSecondary),
                homeCategory = HomeCategory.PHONES
            ),
            CategoryItem(
                name = resources.getString(R.string.computer),
                iconActiveId = R.drawable.ic_computer_active,
                iconInactiveId = R.drawable.ic_computer_inactive,
                backgroundActiveId = requireContext().getColorFromAttr(android.R.attr.colorPrimary),
                backgroundInactiveId = requireContext().getColorFromAttr(android.R.attr.textColor),
                textColorActiveId = requireContext().getColorFromAttr(android.R.attr.colorPrimary),
                textColorInactiveId = requireContext().getColorFromAttr(android.R.attr.colorSecondary),
                homeCategory = HomeCategory.COMPUTERS
            ),
            CategoryItem(
                name = resources.getString(R.string.health),
                iconActiveId = R.drawable.ic_health_active,
                iconInactiveId = R.drawable.ic_health_inactive,
                backgroundActiveId = requireContext().getColorFromAttr(android.R.attr.colorPrimary),
                backgroundInactiveId = requireContext().getColorFromAttr(android.R.attr.textColor),
                textColorActiveId = requireContext().getColorFromAttr(android.R.attr.colorPrimary),
                textColorInactiveId = requireContext().getColorFromAttr(android.R.attr.colorSecondary),
                homeCategory = HomeCategory.HEALTH
            ),
            CategoryItem(
                name = resources.getString(R.string.books),
                iconActiveId = R.drawable.ic_books_active,
                iconInactiveId = R.drawable.ic_books_inactive,
                backgroundActiveId = requireContext().getColorFromAttr(android.R.attr.colorPrimary),
                backgroundInactiveId = requireContext().getColorFromAttr(android.R.attr.textColor),
                textColorActiveId = requireContext().getColorFromAttr(android.R.attr.colorPrimary),
                textColorInactiveId = requireContext().getColorFromAttr(android.R.attr.colorSecondary),
                homeCategory = HomeCategory.BOOKS
            ),
            CategoryItem(
                name = resources.getString(R.string.tools),
                iconActiveId = R.drawable.ic_tool_active,
                iconInactiveId = R.drawable.ic_tool_inactive,
                backgroundActiveId = requireContext().getColorFromAttr(android.R.attr.colorPrimary),
                backgroundInactiveId = requireContext().getColorFromAttr(android.R.attr.textColor),
                textColorActiveId = requireContext().getColorFromAttr(android.R.attr.colorPrimary),
                textColorInactiveId = requireContext().getColorFromAttr(android.R.attr.colorSecondary),
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
        val viewModelFactory = homeAssistedViewModelFactory.create(this)
        homeViewModel =
            ViewModelProvider(requireActivity(), viewModelFactory)[HomeViewModel::class.java]
    }
}
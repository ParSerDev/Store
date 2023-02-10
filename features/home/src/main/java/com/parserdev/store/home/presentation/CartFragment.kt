package com.parserdev.store.home.presentation

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.ListPreloader
import com.bumptech.glide.RequestBuilder
import com.parserdev.store.domain.models.home.cart.CartItem
import com.parserdev.store.domain.network.NetworkResult
import com.parserdev.store.home.R
import com.parserdev.store.home.databinding.FragmentCartBinding
import com.parserdev.store.home.di.HomeComponentProvider
import com.parserdev.store.utility.formatCurrency
import com.parserdev.store.utility.getDp
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class CartFragment : Fragment() {
    @Inject
    lateinit var homeAssistedViewModelFactory: HomeViewModelAssistedFactory
    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            homeAssistedViewModelFactory.create(requireActivity())
        )[HomeViewModel::class.java]
    }

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectCartComponent()
        binding.bindState()
    }

    private fun FragmentCartBinding.bindState() {
        bindNavigation()
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            homeViewModel.cartContent.collect { networkResult ->
                when (networkResult) {
                    is NetworkResult.Success -> {
                        val data = networkResult.data
                        bindRecyclerView(cartItems = data?.cartItems)
                        bindPrice(totalPrice = data?.total, deliveryPrice = data?.delivery)
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

    private fun FragmentCartBinding.bindRecyclerView(
        cartItems: List<CartItem?>?
    ) {
        recyclerView.adapter =
            CartContentAdapter(
                cartItems = cartItems,
                deleteClickListener = {},
                addItemClickListener = {},
                removeItemClickListener = {},
                marginTop = resources.getDp(pixels = 34F).toInt(),
                marginBottom = resources.getDp(pixels = 34F).toInt()
            )
    }

    private fun FragmentCartBinding.bindNavigation() {
        buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun FragmentCartBinding.bindPrice(
        totalPrice: Int?,
        deliveryPrice: String?
    ) {
        textTotalPrice.text = String.format(
            resources.getString(R.string.format_currency), formatCurrency(
                price = totalPrice,
                maximumFractionDigits = 0,
                currencyCode = "USD"
            )
        )
        textDeliveryPrice.text = deliveryPrice
    }

    private fun injectCartComponent() {
        val cartComponent =
            (activity?.application as HomeComponentProvider).provideHomeComponent()
        cartComponent.inject(this)

    }
}
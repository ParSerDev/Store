package com.parserdev.store.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy
import com.parserdev.store.cart.databinding.FragmentCartBinding
import com.parserdev.store.cart.di.CartComponentProvider
import com.parserdev.store.domain.models.cart.CartItem
import com.parserdev.store.domain.network.NetworkResult
import com.parserdev.store.utility.formatCurrency
import com.parserdev.store.utility.getDp
import kotlinx.coroutines.launch
import javax.inject.Inject

class CartFragment : Fragment() {
    @Inject
    lateinit var cartViewModelFactory: CartViewModelFactory
    private lateinit var cartViewModel: CartViewModel

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
        provideViewModel()
        binding.bindState()
    }

    private fun FragmentCartBinding.bindState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                cartViewModel.cartContent.collect { networkResult ->
                    when (networkResult) {
                        is NetworkResult.Success -> {
                            val data = networkResult.data
                            bindNavigation()
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
        recyclerView.adapter?.stateRestorationPolicy =
            StateRestorationPolicy.PREVENT_WHEN_EMPTY
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
                marginTop = resources.getDp(pixels = 34F),
                marginBottom = resources.getDp(pixels = 34F)
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
            (activity?.application as CartComponentProvider).provideCartComponent()
        cartComponent.inject(this)

    }

    private fun provideViewModel() {
        cartViewModel =
            ViewModelProvider(
                this,
                cartViewModelFactory
            )[CartViewModel::class.java]
    }
}
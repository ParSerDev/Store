package com.parserdev.store.cart

import android.os.Bundle
import android.util.Log
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
import com.parserdev.store.domain.network.NetworkResult
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.subscribe
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.*
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
        bindRecyclerView()
        recyclerView.adapter?.stateRestorationPolicy =
            StateRestorationPolicy.PREVENT_WHEN_EMPTY

    }

    private fun FragmentCartBinding.bindRecyclerView() {

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                cartViewModel.cartContent.collect {
                    when (it) {
                        is NetworkResult.Success -> {
                            recyclerView.adapter =
                                CartContentAdapter(
                                    cartItems = it.data?.cartItems,
                                    deleteClickListener = {},
                                    addItemClickListener = {},
                                    removeItemClickListener = {},
                                    marginTop = android.util.TypedValue.applyDimension(
                                        android.util.TypedValue.COMPLEX_UNIT_DIP,
                                        34F,
                                        resources.displayMetrics
                                    ).toInt(),
                                    marginBottom = android.util.TypedValue.applyDimension(
                                        android.util.TypedValue.COMPLEX_UNIT_DIP,
                                        34F,
                                        resources.displayMetrics
                                    ).toInt()
                                )
                            val format: NumberFormat = NumberFormat.getCurrencyInstance()
                            format.maximumFractionDigits = 0
                            format.currency = Currency.getInstance("USD")
                            textTotalPrice.text = (format.format((it.data?.total ?: 0)).toString()) + " us"
                            textDeliveryPrice.text = it.data?.delivery
                            buttonBack.setOnClickListener {
                                findNavController().popBackStack()
                            }
                        }
                        else -> {}
                    }
                }
            }
        }

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
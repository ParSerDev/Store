package com.parserdev.store.home.presentation.home

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.parserdev.store.domain.models.home.Category
import com.parserdev.store.domain.models.home.HomeCategory
import com.parserdev.ui_components.R
import com.parserdev.store.home.databinding.FragmentHomeBinding
import com.parserdev.store.home.presentation.getColorFromAttr
import com.parserdev.store.home.presentation.home.adapters.HotSalesDelegateAdapter
import com.parserdev.store.home.presentation.home.adapters.SearchFieldDelegateAdapter
import com.parserdev.store.home.presentation.home.adapters.SelectCategoryDelegateAdapter
import com.parserdev.store.home.presentation.home.adapters.delegate.CompositeAdapter
import com.parserdev.store.home.presentation.home.adapters.delegate.DelegateAdapterItem
import com.parserdev.store.home.presentation.home.adapters.model.HotSalesItem
import com.parserdev.store.home.presentation.home.adapters.model.SearchFieldItem
import com.parserdev.store.home.presentation.home.adapters.model.SelectCategoryItem

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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
        val spinner = binding.spinner
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.locations_array,
            R.layout.spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
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
        val compositeAdapter = CompositeAdapter.Builder().add(
            SelectCategoryDelegateAdapter(
            clickListener = {},
            marginRight = marginRight,
            marginLeft = marginLeft
        )
        ).add(SearchFieldDelegateAdapter())
            .add(HotSalesDelegateAdapter())
            .build()
        val categories = listOf(
            Category(
                name = resources.getString(R.string.phones),
                iconActiveId = R.drawable.ic_phone_active,
                iconInactiveId = R.drawable.ic_phone_inactive,
                backgroundActiveId = R.drawable.shape_rounded_corners_primary,
                backgroundInactiveId = R.drawable.shape_rounded_corners_onprimary,
                textColorActiveId = requireContext().getColorFromAttr(android.R.attr.textColorPrimary),
                textColorInactiveId = requireContext().getColorFromAttr(android.R.attr.textColorSecondary),
                homeCategory = HomeCategory.PHONES
            ),
            Category(
                name = resources.getString(R.string.computer),
                iconActiveId = R.drawable.ic_computer_active,
                iconInactiveId = R.drawable.ic_computer_inactive,
                backgroundActiveId = R.drawable.shape_rounded_corners_primary,
                backgroundInactiveId = R.drawable.shape_rounded_corners_onprimary,
                textColorActiveId = requireContext().getColorFromAttr(android.R.attr.textColorPrimary),
                textColorInactiveId = requireContext().getColorFromAttr(android.R.attr.textColorSecondary),
                homeCategory = HomeCategory.COMPUTERS
            ),
            Category(
                name = resources.getString(R.string.health),
                iconActiveId = R.drawable.ic_health_active,
                iconInactiveId = R.drawable.ic_health_inactive,
                backgroundActiveId = R.drawable.shape_rounded_corners_primary,
                backgroundInactiveId = R.drawable.shape_rounded_corners_onprimary,
                textColorActiveId = requireContext().getColorFromAttr(android.R.attr.textColorPrimary),
                textColorInactiveId = requireContext().getColorFromAttr(android.R.attr.textColorSecondary),
                homeCategory = HomeCategory.HEALTH
            ),
            Category(
                name = resources.getString(R.string.books),
                iconActiveId = R.drawable.ic_books_active,
                iconInactiveId = R.drawable.ic_books_inactive,
                backgroundActiveId = R.drawable.shape_rounded_corners_primary,
                backgroundInactiveId = R.drawable.shape_rounded_corners_onprimary,
                textColorActiveId = requireContext().getColorFromAttr(android.R.attr.textColorPrimary),
                textColorInactiveId = requireContext().getColorFromAttr(android.R.attr.textColorSecondary),
                homeCategory = HomeCategory.BOOKS
            ),
            Category(
                name = resources.getString(R.string.tools),
                iconActiveId = R.drawable.ic_tool_active,
                iconInactiveId = R.drawable.ic_tool_inactive,
                backgroundActiveId = R.drawable.shape_rounded_corners_primary,
                backgroundInactiveId = R.drawable.shape_rounded_corners_onprimary,
                textColorActiveId = requireContext().getColorFromAttr(android.R.attr.textColorPrimary),
                textColorInactiveId = requireContext().getColorFromAttr(android.R.attr.textColorSecondary),
                homeCategory = HomeCategory.TOOLS
            )
        )
        binding.recyclerHome.adapter = compositeAdapter
        compositeAdapter.submitList(
            listOf<DelegateAdapterItem>(
                SelectCategoryItem(
                        categories = categories
                ),
                SearchFieldItem(
                    hint = getString(R.string.search)
                ),
                HotSalesItem(
                    items = listOf()
                )
            )
        )


    }
}
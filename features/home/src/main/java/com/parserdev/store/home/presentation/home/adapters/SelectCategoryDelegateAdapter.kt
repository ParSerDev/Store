package com.parserdev.store.home.presentation.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.parserdev.store.domain.models.home.Category
import com.parserdev.store.home.databinding.ItemDelegateSelectCategoryBinding
import com.parserdev.store.home.presentation.home.adapters.delegate.DelegateAdapter
import com.parserdev.store.home.presentation.home.adapters.delegate.DelegateAdapterItem
import com.parserdev.store.home.presentation.home.adapters.model.SelectCategoryItem

class SelectCategoryDelegateAdapter(
    val clickListener: (Category) -> Unit,
    val marginLeft: Int,
    val marginRight: Int
) :
    DelegateAdapter<SelectCategoryItem, SelectCategoryDelegateAdapter.SelectCategoryViewHolder>(
        SelectCategoryItem::class.java
    ) {
    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding =
            ItemDelegateSelectCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SelectCategoryViewHolder(
            binding = binding,
            clickListener = clickListener,
            marginLeft = marginLeft,
            marginRight = marginRight
        )
    }

    override fun bindViewHolder(
        model: SelectCategoryItem,
        viewHolder: SelectCategoryViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(model)
    }

    class SelectCategoryViewHolder(
        private val binding: ItemDelegateSelectCategoryBinding,
        private val clickListener: (Category) -> Unit,
        private val marginLeft: Int,
        private val marginRight: Int
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SelectCategoryItem) {
            val categoriesAdapter = CategoriesAdapter(
                categories = item.categories,
                clickListener = clickListener,
                marginLeft = marginLeft,
                marginRight = marginRight
            )
            binding.rvCategories.adapter = categoriesAdapter
        }

    }
}
package com.parserdev.store.home.presentation.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.parserdev.store.domain.models.home.CategoryItem
import com.parserdev.store.home.databinding.ItemDelegateSelectCategoryBinding
import com.parserdev.store.home.presentation.home.adapters.delegate.DelegateAdapter
import com.parserdev.store.home.presentation.home.adapters.delegate.DelegateAdapterItem
import com.parserdev.store.home.presentation.home.adapters.model.SelectCategoryListItem

class SelectCategoryDelegateAdapter(
    val clickListener: (CategoryItem) -> Unit,
    val marginLeft: Int,
    val marginRight: Int
) :
    DelegateAdapter<SelectCategoryListItem, SelectCategoryDelegateAdapter.SelectCategoryViewHolder>(
        SelectCategoryListItem::class.java
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
        model: SelectCategoryListItem,
        viewHolder: SelectCategoryViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(model)
    }

    class SelectCategoryViewHolder(
        private val binding: ItemDelegateSelectCategoryBinding,
        private val clickListener: (CategoryItem) -> Unit,
        private val marginLeft: Int,
        private val marginRight: Int
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SelectCategoryListItem) {
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
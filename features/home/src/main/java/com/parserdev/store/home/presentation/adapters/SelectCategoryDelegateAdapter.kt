package com.parserdev.store.home.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.parserdev.store.domain.models.home.CategoryItem
import com.parserdev.store.home.databinding.ItemDelegateSelectCategoryBinding
import com.parserdev.store.home.presentation.adapters.delegate.DelegateAdapter
import com.parserdev.store.home.presentation.adapters.delegate.DelegateAdapterItem
import com.parserdev.store.home.presentation.adapters.model.SelectCategoryListItem

class SelectCategoryDelegateAdapter(
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
        private val marginLeft: Int,
        private val marginRight: Int
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SelectCategoryListItem) {
            val categoriesAdapter = CategoriesAdapter(
                categories = item.categories,
                clickListener = item.clickListener,
                marginLeft = marginLeft,
                marginRight = marginRight
            )
            binding.recyclerView.adapter = categoriesAdapter
        }

    }
}
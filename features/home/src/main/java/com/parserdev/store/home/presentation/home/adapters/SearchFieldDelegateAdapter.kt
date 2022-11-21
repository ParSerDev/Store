package com.parserdev.store.home.presentation.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.parserdev.store.home.databinding.ItemSearchFieldBinding
import com.parserdev.store.home.presentation.home.adapters.delegate.DelegateAdapter
import com.parserdev.store.home.presentation.home.adapters.delegate.DelegateAdapterItem
import com.parserdev.store.home.presentation.home.adapters.model.SearchFieldItem

class SearchFieldDelegateAdapter :
    DelegateAdapter<SearchFieldItem, SearchFieldDelegateAdapter.SearchFieldViewHolder>(
        SearchFieldItem::class.java
    ) {
    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding =
            ItemSearchFieldBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchFieldViewHolder(binding)
    }

    override fun bindViewHolder(
        model: SearchFieldItem,
        viewHolder: SearchFieldViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(model)
    }

    class SearchFieldViewHolder(private val binding: ItemSearchFieldBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SearchFieldItem) {
            binding.editTextSearch.hint = item.hint
        }

    }
}
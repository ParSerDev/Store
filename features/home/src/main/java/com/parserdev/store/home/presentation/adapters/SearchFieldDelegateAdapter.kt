package com.parserdev.store.home.presentation.adapters

import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.RecyclerView
import com.parserdev.store.home.databinding.ItemDelegateSearchFieldBinding
import com.parserdev.store.home.presentation.adapters.delegate.DelegateAdapter
import com.parserdev.store.home.presentation.adapters.delegate.DelegateAdapterItem
import com.parserdev.store.home.presentation.adapters.model.SearchFieldItem

class SearchFieldDelegateAdapter :
    DelegateAdapter<SearchFieldItem, SearchFieldDelegateAdapter.SearchFieldViewHolder>(
        SearchFieldItem::class.java
    ) {
    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding =
            ItemDelegateSearchFieldBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchFieldViewHolder(binding)
    }

    override fun bindViewHolder(
        model: SearchFieldItem,
        viewHolder: SearchFieldViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(item = model)
    }

    class SearchFieldViewHolder(
        private val binding: ItemDelegateSearchFieldBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SearchFieldItem) {
            binding.editTextSearch.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    item.editTextListener.invoke(binding.editTextSearch.text.trim().toString())
                    true
                } else {
                    false
                }
            }
            binding.editTextSearch.setOnKeyListener { _, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    item.editTextListener.invoke(binding.editTextSearch.text.trim().toString())
                    true
                } else {
                    false
                }
            }
        }

    }
}
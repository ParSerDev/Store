package com.parserdev.store.home.presentation.home.adapters

import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.RecyclerView
import com.parserdev.store.home.databinding.ItemSearchFieldBinding
import com.parserdev.store.home.presentation.home.adapters.delegate.DelegateAdapter
import com.parserdev.store.home.presentation.home.adapters.delegate.DelegateAdapterItem
import com.parserdev.store.home.presentation.home.adapters.model.SearchFieldItem

class SearchFieldDelegateAdapter(private val editTextListener: (String) -> Unit) :
    DelegateAdapter<SearchFieldItem, SearchFieldDelegateAdapter.SearchFieldViewHolder>(
        SearchFieldItem::class.java
    ) {
    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding =
            ItemSearchFieldBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchFieldViewHolder(binding, editTextListener)
    }

    override fun bindViewHolder(
        model: SearchFieldItem,
        viewHolder: SearchFieldViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind()
    }

    class SearchFieldViewHolder(
        private val binding: ItemSearchFieldBinding,
        private val editTextListener: (String) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            binding.editTextSearch.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    editTextListener.invoke(binding.editTextSearch.text.trim().toString())
                    true
                } else {
                    false
                }
            }
            binding.editTextSearch.setOnKeyListener { _, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    editTextListener.invoke(binding.editTextSearch.text.trim().toString())
                    true
                } else {
                    false
                }
            }
        }

    }
}
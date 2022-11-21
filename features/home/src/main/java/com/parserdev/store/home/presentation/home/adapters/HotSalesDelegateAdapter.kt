package com.parserdev.store.home.presentation.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.parserdev.store.domain.models.home.HomeCategory
import com.parserdev.store.domain.models.home.HotItem
import com.parserdev.store.home.databinding.ItemHotSalesBinding
import com.parserdev.store.home.databinding.ItemSelectCategoryBinding
import com.parserdev.store.home.presentation.home.adapters.delegate.DelegateAdapter
import com.parserdev.store.home.presentation.home.adapters.delegate.DelegateAdapterItem
import com.parserdev.store.home.presentation.home.adapters.model.HotSalesItem
import com.parserdev.store.home.presentation.home.adapters.model.SelectCategoryItem

class HotSalesDelegateAdapter:
    DelegateAdapter<HotSalesItem, HotSalesDelegateAdapter.HotSalesViewHolder>(
        HotSalesItem::class.java
    ) {
    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding =
            ItemHotSalesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HotSalesViewHolder(
            binding = binding,
        )
    }

    override fun bindViewHolder(
        model: HotSalesItem,
        viewHolder: HotSalesViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(model)
    }

    class HotSalesViewHolder(
        private val binding: ItemHotSalesBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HotSalesItem) {
        }

    }
}
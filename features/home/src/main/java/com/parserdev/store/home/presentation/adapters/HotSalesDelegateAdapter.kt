package com.parserdev.store.home.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import com.parserdev.store.domain.models.home.HotItem
import com.parserdev.store.home.databinding.ItemDelegateHotSalesBinding
import com.parserdev.store.home.presentation.adapters.delegate.DelegateAdapter
import com.parserdev.store.home.presentation.adapters.delegate.DelegateAdapterItem
import com.parserdev.store.home.presentation.adapters.model.HotSalesListItem
import kotlin.math.abs

class HotSalesDelegateAdapter(private val clickListener: (HotItem) -> Unit) :
    DelegateAdapter<HotSalesListItem, HotSalesDelegateAdapter.HotSalesViewHolder>(
        HotSalesListItem::class.java
    ) {
    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding =
            ItemDelegateHotSalesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HotSalesViewHolder(
            binding = binding,
            clickListener = clickListener
        )
    }

    override fun bindViewHolder(
        model: HotSalesListItem,
        viewHolder: HotSalesViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(model)
    }

    class HotSalesViewHolder(
        private val binding: ItemDelegateHotSalesBinding,
        private val clickListener: (HotItem) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HotSalesListItem) {
            binding.apply {
                val hotItemsAdapter = HotItemsAdapter(
                    hotItems = item.items,
                    clickListener = clickListener
                )
                binding.viewPager.apply {
                    clipChildren = false
                    clipToPadding =
                        false
                    offscreenPageLimit = 3
                    (getChildAt(0) as RecyclerView).overScrollMode =
                        RecyclerView.OVER_SCROLL_NEVER
                    adapter = hotItemsAdapter
                    val compositePageTransformer = CompositePageTransformer()
                    compositePageTransformer.addTransformer { page, position ->
                        val r = 1 - abs(position)
                        page.scaleY = (0.80f + r * 0.20f)
                    }
                    viewPager.setPageTransformer(compositePageTransformer)
                }

            }
        }

    }
}
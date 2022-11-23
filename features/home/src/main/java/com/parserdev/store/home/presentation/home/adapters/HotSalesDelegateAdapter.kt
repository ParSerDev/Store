package com.parserdev.store.home.presentation.home.adapters

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.parserdev.store.domain.models.home.HomeCategory
import com.parserdev.store.home.databinding.ItemHotSalesBinding
import com.parserdev.store.home.presentation.home.adapters.delegate.DelegateAdapter
import com.parserdev.store.home.presentation.home.adapters.delegate.DelegateAdapterItem
import com.parserdev.store.home.presentation.home.adapters.model.HotSalesItem
import kotlin.math.abs

class HotSalesDelegateAdapter(private val clickListener: (HomeCategory) -> Unit) :
    DelegateAdapter<HotSalesItem, HotSalesDelegateAdapter.HotSalesViewHolder>(
        HotSalesItem::class.java
    ) {
    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding =
            ItemHotSalesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HotSalesViewHolder(
            binding = binding,
            clickListener = clickListener
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
        private val binding: ItemHotSalesBinding,
        private val clickListener: (HomeCategory) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HotSalesItem) {
            binding.apply {
                val hotItemsAdapter = HotItemsAdapter(
                    hotItems = item.items,
                    clickListener = clickListener
                )
                binding.viewPagerHotSales.apply {
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
                    viewPagerHotSales.setPageTransformer(compositePageTransformer)
                }

            }
        }

    }
}
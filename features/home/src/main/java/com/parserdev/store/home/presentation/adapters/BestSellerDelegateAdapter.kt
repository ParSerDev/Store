package com.parserdev.store.home.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parserdev.store.data.dto.home.FavouriteItemDto
import com.parserdev.store.home.databinding.ItemDelegateBestSellerBinding
import com.parserdev.store.home.presentation.adapters.delegate.DelegateAdapter
import com.parserdev.store.home.presentation.adapters.delegate.DelegateAdapterItem
import com.parserdev.store.home.presentation.adapters.model.BestSellersListItem

class BestSellerDelegateAdapter(
    private val likeClickListener: (FavouriteItemDto) -> Unit,
    private val navigationClickListener: (String) -> Unit,
    private val gridLayoutManager: GridLayoutManager,
    private val marginLeft: Int,
    private val marginRight: Int
) :
    DelegateAdapter<BestSellersListItem, BestSellerDelegateAdapter.BestSellerDelegateViewHolder>(
        BestSellersListItem::class.java
    ) {
    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding =
            ItemDelegateBestSellerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BestSellerDelegateViewHolder(
            binding = binding,
            likeClickListener = likeClickListener,
            navigationClickListener = navigationClickListener,
            gridLayoutManager = gridLayoutManager,
            marginLeft = marginLeft,
            marginRight = marginRight
        )
    }

    override fun bindViewHolder(
        model: BestSellersListItem,
        viewHolder: BestSellerDelegateViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(model)
    }

    class BestSellerDelegateViewHolder(
        private val binding: ItemDelegateBestSellerBinding,
        private val gridLayoutManager: GridLayoutManager,
        private val likeClickListener: (FavouriteItemDto) -> Unit,
        private val navigationClickListener: (String) -> Unit,
        private val marginLeft: Int,
        private val marginRight: Int
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BestSellersListItem) {
            binding.apply {
                recyclerView.layoutManager = gridLayoutManager
                recyclerView.adapter = BestSellersAdapter(
                    bestSellers = item.bestSellers,
                    likeClickListener = likeClickListener,
                    navigationClickListener = navigationClickListener,
                    marginLeft = marginLeft,
                    marginRight = marginRight
                )
            }
        }

    }
}
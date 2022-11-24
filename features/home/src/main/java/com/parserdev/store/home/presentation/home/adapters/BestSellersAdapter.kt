package com.parserdev.store.home.presentation.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.parserdev.store.data.dto.home.FavouriteItemDto
import com.parserdev.store.domain.models.home.BestSellerItem
import com.parserdev.store.home.databinding.ItemBestSellerBinding
import com.parserdev.ui_components.R

class BestSellersAdapter(
    val bestSellers: List<BestSellerItem>?,
    val likeClickListener: (FavouriteItemDto) -> Unit,
    val navigationClickListener: (String) -> Unit,
    val marginLeft: Int,
    val marginRight: Int
) :
    ListAdapter<BestSellerItem, BestSellersAdapter.BestSellersViewHolder>(DiffCallBack()) {

    class BestSellersViewHolder(
        val binding: ItemBestSellerBinding
    ) : RecyclerView.ViewHolder(binding.root)

    class DiffCallBack : DiffUtil.ItemCallback<BestSellerItem>() {
        override fun areItemsTheSame(
            oldItem: BestSellerItem,
            newItem: BestSellerItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: BestSellerItem,
            newItem: BestSellerItem
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellersViewHolder {
        val binding = ItemBestSellerBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return BestSellersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BestSellersViewHolder, position: Int) {
        val adapterPosition = holder.adapterPosition
        holder.binding.apply {
            if (bestSellers != null) {
                Glide.with(imagePicture.context).load(bestSellers[adapterPosition].picture)
                    .centerCrop()
                    .into(imagePicture)
                textTitle.text = bestSellers[adapterPosition].title
                textPrice.text = bestSellers[adapterPosition].discountPrice.toString()
                textOldPrice.text = bestSellers[adapterPosition].priceWithoutDiscount.toString()
                when(bestSellers[adapterPosition].isFavorites) {
                    true -> imageLike.setImageResource(R.drawable.ic_heart_primary_active)
                    else ->  imageLike.setImageResource(R.drawable.ic_heart_primary_inactive)
                }
                buttonLike.setOnClickListener { likeClickListener }
                layout.setOnClickListener { navigationClickListener }
                if(adapterPosition%2==0) {
                    (layout.layoutParams as MarginLayoutParams).leftMargin = marginLeft
                } else {
                    (layout.layoutParams as MarginLayoutParams).rightMargin = marginRight
                }
            }
        }
    }

    override fun getItemCount() = bestSellers?.size ?: 0

}
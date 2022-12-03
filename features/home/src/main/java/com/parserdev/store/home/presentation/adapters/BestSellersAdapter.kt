package com.parserdev.store.home.presentation.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.parserdev.store.data.dto.home.FavouriteItemDto
import com.parserdev.store.domain.models.home.BestSellerItem
import com.parserdev.store.home.databinding.ItemBestSellerBinding
import com.parserdev.ui_components.R
import java.text.NumberFormat
import java.util.*

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
        val adapterPosition = holder.bindingAdapterPosition
        holder.binding.apply {
            if (bestSellers != null) {
                Glide.with(imagePicture.context).load(bestSellers[adapterPosition].picture)
                    .centerCrop()
                    .into(imagePicture)
                textTitle.text = bestSellers[adapterPosition].title
                val format: NumberFormat = NumberFormat.getCurrencyInstance()
                format.maximumFractionDigits = 0
                format.currency = Currency.getInstance("USD")
                textPrice.text =
                    format.format(bestSellers[adapterPosition].discountPrice).toString()
                textOldPrice.apply {
                    paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    text =
                        format.format(bestSellers[adapterPosition].priceWithoutDiscount).toString()
                }
                when (bestSellers[adapterPosition].isFavorites) {
                    true -> image.setImageResource(R.drawable.ic_heart_primary_active)
                    else -> image.setImageResource(R.drawable.ic_heart_primary_inactive)
                }
                button.setOnClickListener { likeClickListener }
                cardItem.setOnClickListener { navigationClickListener.invoke("https://run.mocky.io/v3/6c14c560-15c6-4248-b9d2-b4508df7d4f5") }
                if (adapterPosition % 2 == 0) {
                    (cardItem.layoutParams as MarginLayoutParams).leftMargin = marginLeft
                } else {
                    (cardItem.layoutParams as MarginLayoutParams).rightMargin = marginRight
                }
            }
        }
    }

    override fun getItemCount() = bestSellers?.size ?: 0

}
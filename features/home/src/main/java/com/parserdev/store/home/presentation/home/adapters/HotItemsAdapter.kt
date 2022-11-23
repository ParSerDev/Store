package com.parserdev.store.home.presentation.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.parserdev.store.domain.models.home.HomeCategory
import com.parserdev.store.domain.models.home.HotItem
import com.parserdev.store.home.databinding.ItemHotBinding

class HotItemsAdapter(
    val hotItems: List<HotItem>?,
    val clickListener: (HomeCategory) -> Unit,
) :
    RecyclerView.Adapter<HotItemsAdapter.HotItemsViewHolder>() {
    class HotItemsViewHolder(
        val binding: ItemHotBinding
    ) : RecyclerView.ViewHolder(binding.root)

    class DiffCallBack : DiffUtil.ItemCallback<HotItem>() {
        override fun areItemsTheSame(
            oldItem: HotItem,
            newItem: HotItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: HotItem,
            newItem: HotItem
        ): Boolean {
            return oldItem == newItem
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotItemsViewHolder {
        val binding = ItemHotBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return HotItemsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HotItemsViewHolder, position: Int) {
        val adapterPosition = holder.adapterPosition
        holder.binding.apply {
            if (hotItems != null) {
                Glide.with(imageItemPicture.context).load(hotItems[adapterPosition].picture)
                    .centerCrop()
                    .into(imageItemPicture)
                textTitle.text = hotItems[adapterPosition].title
                textSubtitle.text = hotItems[adapterPosition].subtitle
                buttonBuy.setOnClickListener { clickListener }
                if (hotItems[adapterPosition].isNew == false || hotItems[adapterPosition].isNew == null) {
                    layoutNew.visibility = View.INVISIBLE
                    textNew.visibility = View.INVISIBLE
                }
            }
        }
    }

    override fun getItemCount() = hotItems?.size ?: 0


}
package com.parserdev.store.home.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.parserdev.store.domain.models.home.CategoryItem
import com.parserdev.store.home.databinding.ItemCategoryBinding

class CategoriesAdapter(
    val categories: List<CategoryItem>,
    val clickListener: (CategoryItem) -> Unit,
    val marginLeft: Int,
    val marginRight: Int
) :
    ListAdapter<CategoryItem, CategoriesAdapter.CategoriesViewHolder>(DiffCallBack()) {
    private var selectedItemPosition: Int = 0

    class CategoriesViewHolder(
        val binding: ItemCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root)

    class DiffCallBack : DiffUtil.ItemCallback<CategoryItem>() {
        override fun areItemsTheSame(
            oldItem: CategoryItem,
            newItem: CategoryItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: CategoryItem,
            newItem: CategoryItem
        ): Boolean {
            return oldItem.homeCategory == newItem.homeCategory
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val binding = ItemCategoryBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.binding.apply {
            val adapterPosition = holder.bindingAdapterPosition
            val startPosition = 0
            val endPosition = categories.size - 1
            card.setOnClickListener {
                selectedItemPosition = adapterPosition
                clickListener
                notifyDataSetChanged()
            }
            when (adapterPosition) {
                startPosition -> (layout.layoutParams as MarginLayoutParams).leftMargin = marginLeft
                endPosition -> (layout.layoutParams as MarginLayoutParams).rightMargin = marginRight
            }
            when (selectedItemPosition) {
                adapterPosition -> {
                    card.isClickable = false
                    val iconActive = categories[adapterPosition].iconActiveId
                    val backgroundActive = categories[adapterPosition].backgroundActiveId
                    val textColorActive = categories[adapterPosition].textColorActiveId
                    image.setImageResource(iconActive)
                    card.setCardBackgroundColor(backgroundActive)
                    text.setTextColor(textColorActive)
                }
                else -> {
                    card.isClickable = true
                    val iconInactive = categories[adapterPosition].iconInactiveId
                    val backgroundInactive = categories[adapterPosition].backgroundInactiveId
                    val textColorInactive = categories[adapterPosition].textColorInactiveId
                    image.setImageResource(iconInactive)
                    card.setCardBackgroundColor(backgroundInactive)
                    text.setTextColor(textColorInactive)
                }
            }
            text.text = categories[adapterPosition].name
        }
    }

    override fun getItemCount() = categories.size


}
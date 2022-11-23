package com.parserdev.store.home.presentation.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.parserdev.store.domain.models.home.Category
import com.parserdev.store.domain.models.home.HomeCategory
import com.parserdev.store.home.databinding.ItemCategoryBinding

class CategoriesAdapter(
    val categories: List<Category>,
    val clickListener: (HomeCategory) -> Unit,
    val marginLeft: Int,
    val marginRight: Int
) :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {
    private var selectedItemPosition: Int = 0

    class CategoriesViewHolder(
        val binding: ItemCategoryBinding,
        val clickListener: (HomeCategory) -> Unit
    ) : RecyclerView.ViewHolder(binding.root)

    class DiffCallBack : DiffUtil.ItemCallback<HomeCategory>() {
        override fun areItemsTheSame(
            oldItem: HomeCategory,
            newItem: HomeCategory
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: HomeCategory,
            newItem: HomeCategory
        ): Boolean {
            return oldItem == newItem
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val binding = ItemCategoryBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriesViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.binding.apply {
            val adapterPosition = holder.adapterPosition
            val startPosition = 0
            val endPosition = categories.size - 1
            buttonCategory.setOnClickListener {
                selectedItemPosition = adapterPosition
                notifyDataSetChanged()
            }
            
            when(adapterPosition) {
                startPosition -> (layout.layoutParams as MarginLayoutParams).leftMargin = marginLeft
                endPosition -> (layout.layoutParams as MarginLayoutParams).rightMargin = marginRight
            }
            when(selectedItemPosition) {
                adapterPosition -> {
                    buttonCategory.isClickable = false
                    val iconActive = categories[adapterPosition].iconActiveId
                    val backgroundActive = categories[adapterPosition].backgroundActiveId
                    val textColorActive = categories[adapterPosition].textColorActiveId
                    imageCategoryIcon.setImageResource(iconActive)
                    buttonCategory.setBackgroundResource(backgroundActive)
                    textName.setTextColor(textColorActive)
                }
                else ->  {
                    buttonCategory.isClickable = true
                    val iconInactive = categories[adapterPosition].iconInactiveId
                    val backgroundInactive = categories[adapterPosition].backgroundInactiveId
                    val textColorInactive = categories[adapterPosition].textColorInactiveId
                    imageCategoryIcon.setImageResource(iconInactive)
                    buttonCategory.setBackgroundResource(backgroundInactive)
                    textName.setTextColor(textColorInactive)
                }
            }

             textName.text = categories[adapterPosition].name

        }
    }

    override fun getItemCount() = categories.size


}
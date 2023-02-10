package com.parserdev.store.home.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.parserdev.store.domain.models.home.cart.CartItem
import com.parserdev.store.home.databinding.ItemCartBinding
import java.text.NumberFormat
import java.util.*

class CartContentAdapter(
    val cartItems: List<CartItem?>?,
    val deleteClickListener: (CartItem?) -> Unit,
    val addItemClickListener: (CartItem?) -> Unit,
    val removeItemClickListener: (CartItem?) -> Unit,
    val marginTop: Int,
    val marginBottom: Int
) :
    ListAdapter<CartItem, CartContentAdapter.CartContentViewHolder>(DiffCallBack()) {

    class CartContentViewHolder(
        val binding: ItemCartBinding
    ) : RecyclerView.ViewHolder(binding.root)

    class DiffCallBack : DiffUtil.ItemCallback<CartItem>() {
        override fun areItemsTheSame(
            oldItem: CartItem,
            newItem: CartItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: CartItem,
            newItem: CartItem
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartContentViewHolder {
        val binding = ItemCartBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CartContentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartContentViewHolder, position: Int) {
        if (cartItems != null) {
            holder.binding.apply {
                val adapterPosition = holder.bindingAdapterPosition
                val startPosition = 0
                val endPosition = cartItems.size - 1
                Glide.with(imagePicture.context).load(cartItems[position]?.images)
                    .centerCrop()
                    .into(imagePicture)
                val format: NumberFormat = NumberFormat.getCurrencyInstance()
                format.maximumFractionDigits = 2
                format.currency = Currency.getInstance("USD")
                textPrice.text =
                    format.format(cartItems[position]?.price).toString()
                textTitle.text = cartItems[position]?.title
                textAmount.text = (cartItems[position]?.amount ?: 1).toString()
                when (adapterPosition) {
                    startPosition -> (layout.layoutParams as ViewGroup.MarginLayoutParams).topMargin =
                        marginTop
                    endPosition -> (layout.layoutParams as ViewGroup.MarginLayoutParams).bottomMargin =
                        marginBottom
                }
                imageDelete.setOnClickListener {
                    deleteClickListener.invoke(cartItems[position])
                }
                buttonPlus.setOnClickListener {
                    addItemClickListener.invoke(cartItems[position])
                }
                buttonMinus.setOnClickListener {
                    removeItemClickListener.invoke(cartItems[position])
                }

            }
        }
    }

    override fun getItemCount() = cartItems?.size ?: -1


}
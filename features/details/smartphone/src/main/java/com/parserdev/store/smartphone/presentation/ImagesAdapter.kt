package com.parserdev.store.smartphone.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.parserdev.store.domain.models.details.Image
import com.parserdev.store.smartphone.databinding.ItemImageBinding

class ImagesAdapter(
    val images: List<Image>?
) :
    ListAdapter<Image, ImagesAdapter.ImagesViewHolder>(DiffCallBack()) {
    class ImagesViewHolder(
        val binding: ItemImageBinding
    ) : RecyclerView.ViewHolder(binding.root)

    class DiffCallBack : DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(
            oldItem: Image,
            newItem: Image
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Image,
            newItem: Image
        ): Boolean {
            return oldItem.url == newItem.url
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val binding = ItemImageBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ImagesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        val adapterPosition = holder.adapterPosition
        holder.binding.apply {
            if (images != null) {
                Glide.with(imagePicture.context).load(images[adapterPosition].url)
                    .centerCrop()
                    .into(imagePicture)
            }
        }
    }

    override fun getItemCount() = images?.size ?: 0


}
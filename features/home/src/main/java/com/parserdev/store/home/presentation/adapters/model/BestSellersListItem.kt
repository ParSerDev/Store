package com.parserdev.store.home.presentation.adapters.model

import com.parserdev.store.domain.models.home.BestSellerItem
import com.parserdev.store.home.presentation.adapters.delegate.DelegateAdapterItem

data class BestSellersListItem(
    val bestSellers: List<BestSellerItem>?,
    val likeClickListener: (Int) -> Unit,
    val navigationClickListener: (String) -> Unit
) : DelegateAdapterItem {
    override fun id(): Any = BestSellersListItem::class.toString()
    override fun content(): Any = bestSellers?:-1
    inner class BestSellersListContent(private val bestSellers: List<BestSellerItem>) {
        override fun equals(other: Any?): Boolean {
            if (other is BestSellersListItem) {
                return bestSellers == other.bestSellers
            }
            return false
        }

        override fun hashCode(): Int {
            var result = bestSellers.hashCode()
            result = 31 * result + bestSellers.hashCode()
            return result
        }
    }
}
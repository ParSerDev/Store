package com.parserdev.store.home.presentation.home.adapters.model

import com.parserdev.store.domain.models.home.BestSellerItem
import com.parserdev.store.home.presentation.home.adapters.delegate.DelegateAdapterItem

data class BestSellersListItem(
    val bestSellers: List<BestSellerItem>
) : DelegateAdapterItem {
    override fun id(): Any = BestSellersListItem::class.toString()
    override fun content(): Any = bestSellers
    inner class BestSellersListContent(val bestSellers: List<BestSellerItem>) {
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
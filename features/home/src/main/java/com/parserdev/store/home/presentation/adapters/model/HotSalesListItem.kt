package com.parserdev.store.home.presentation.adapters.model

import com.parserdev.store.domain.models.home.HotItem
import com.parserdev.store.home.presentation.adapters.delegate.DelegateAdapterItem

data class HotSalesListItem(
    val items: List<HotItem>?
) : DelegateAdapterItem {
    override fun id(): Any = HotSalesListItem::class.toString()
    override fun content(): Any = items?:-1
    inner class HotSalesContent(private val items: List<HotItem>) {
        override fun equals(other: Any?): Boolean {
            if (other is HotSalesContent) {
                return items == other.items
            }
            return false
        }

        override fun hashCode(): Int {
            var result = items.hashCode()
            result = 31 * result + items.hashCode()
            return result
        }
    }
}
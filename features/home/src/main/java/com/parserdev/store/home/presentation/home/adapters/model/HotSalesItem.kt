package com.parserdev.store.home.presentation.home.adapters.model

import com.parserdev.store.domain.models.home.HotItem
import com.parserdev.store.home.presentation.home.adapters.delegate.DelegateAdapterItem

data class HotSalesItem(
    val items: List<HotItem>
) : DelegateAdapterItem {
    override fun id(): Any = HotSalesItem::class.toString()
    override fun content(): Any = items
    inner class HotSalesContent(val items: List<HotItem>) {
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
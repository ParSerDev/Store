package com.parserdev.store.home.presentation.adapters.model

import com.parserdev.store.home.presentation.adapters.delegate.DelegateAdapterItem

class SearchFieldItem(
    val editTextListener: (String) -> Unit
) : DelegateAdapterItem {
    override fun id(): Any = SearchFieldItem::class.toString()
    override fun content(): Any = 0
    override fun equals(other: Any?): Boolean {
        return other is SearchFieldItem
    }
    override fun hashCode(): Int {
        var result = this.hashCode()
        result += 31 * result
        return result
    }
}

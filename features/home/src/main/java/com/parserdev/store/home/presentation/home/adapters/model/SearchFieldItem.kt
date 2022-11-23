package com.parserdev.store.home.presentation.home.adapters.model

import com.parserdev.store.home.presentation.home.adapters.delegate.DelegateAdapterItem

class SearchFieldItem : DelegateAdapterItem {
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

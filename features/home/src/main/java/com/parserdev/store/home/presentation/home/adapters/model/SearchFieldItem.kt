package com.parserdev.store.home.presentation.home.adapters.model

import com.parserdev.store.home.presentation.home.adapters.delegate.DelegateAdapterItem

data class SearchFieldItem(
    val hint: String
) : DelegateAdapterItem {
    override fun id(): Any = SearchFieldItem::class.toString()
    override fun content(): Any = hint
    inner class SearchFieldContent(val hint: String) {
        override fun equals(other: Any?): Boolean {
            if (other is SearchFieldContent) {
                return hint == other.hint
            }
            return false
        }

        override fun hashCode(): Int {
            var result = hint.hashCode()
            result = 31 * result + this@SearchFieldItem.hint.hashCode()
            return result
        }
    }
}
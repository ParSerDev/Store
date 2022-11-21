package com.parserdev.store.home.presentation.home.adapters.model

import com.parserdev.store.domain.models.home.Category
import com.parserdev.store.home.presentation.home.adapters.delegate.DelegateAdapterItem

data class SelectCategoryItem(
    val categories: List<Category>
) : DelegateAdapterItem {
    override fun id(): Any = SelectCategoryItem::class.toString()
    override fun content(): Any = categories
    inner class SelectCategoryContent(val name: String) {
        override fun equals(other: Any?): Boolean {
            if (other is SelectCategoryContent) {
                return name == other.name
            }
            return false
        }

        override fun hashCode(): Int {
            var result = name.hashCode()
            result = 31 * result + categories.hashCode()
            return result
        }
    }
}
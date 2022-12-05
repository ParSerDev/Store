package com.parserdev.store.home.presentation.adapters.model

import com.parserdev.store.domain.models.home.CategoryItem
import com.parserdev.store.home.presentation.adapters.delegate.DelegateAdapterItem

data class SelectCategoryListItem(
    val categories: List<CategoryItem>,
    val clickListener: (CategoryItem) -> Unit,
) : DelegateAdapterItem {
    override fun id(): Any = SelectCategoryListItem::class.toString()
    override fun content(): Any = categories
    inner class SelectCategoryContent(private val name: String) {
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
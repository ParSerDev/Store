package com.parserdev.store.domain.models.home

class HotItem(
    val subtitle: String? = null,
    val id: Int? = null,
    val title: String? = null,
    val picture: String? = null,
    val isBuy: Boolean? = null,
    val isNew: Boolean? = null
) {
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}
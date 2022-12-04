package com.parserdev.store.utility

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import java.text.NumberFormat
import java.util.*

@ColorInt
fun Context.getColorFromAttr(
    @AttrRes attrColor: Int,
    typedValue: TypedValue = TypedValue(),
    resolveRefs: Boolean = true
): Int {
    theme.resolveAttribute(attrColor, typedValue, resolveRefs)
    return typedValue.data
}

fun formatCurrency(
    price: Int?,
    maximumFractionDigits: Int,
    currencyCode: String
): String? {
    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = maximumFractionDigits
    format.currency = Currency.getInstance(currencyCode)
    return format.format(price)
}

fun Resources.getDp(pixels: Float): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        pixels,
        displayMetrics
    ).toInt()
}
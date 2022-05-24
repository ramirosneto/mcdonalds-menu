package br.com.mcdonalds.menu.utils

import android.annotation.SuppressLint
import java.text.NumberFormat

object CurrencyUtils {

    @SuppressLint("NewApi")
    fun formatPrice(price: Double): String {
        val numberFormat = NumberFormat.getCurrencyInstance()
        numberFormat.maximumFractionDigits = 2;
        return numberFormat.format(price)
    }
}

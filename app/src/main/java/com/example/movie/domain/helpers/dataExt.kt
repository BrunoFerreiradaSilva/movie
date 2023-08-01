package com.example.movie.domain.helpers

import java.text.SimpleDateFormat
import java.util.Locale

fun String.convertBrData(): String {
    val formatOrigin = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val data = formatOrigin.parse(this)

    val formatBr = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatBr.format(data)
}
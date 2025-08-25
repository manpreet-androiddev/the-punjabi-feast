package com.app.thepunjabifeast.extensions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.formatDateOfBirth(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    return sdf.format(this)
}

fun String.capitalizeWords(): String = split(" ").joinToString(" ") {
    it.replaceFirstChar { it ->
        if (it.isLowerCase()) it.titlecase(
            Locale.getDefault()
        ) else it.toString()
    }
}

fun Date.isWithinPast(minutes: Int): Boolean {
    val now = Date()
    val timeAgo = Date(now.time - (60 * minutes * 1000))
    val range = timeAgo..now
    return range.contains(this)
}
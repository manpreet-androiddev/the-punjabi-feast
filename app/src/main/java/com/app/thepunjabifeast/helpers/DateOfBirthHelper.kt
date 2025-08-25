package com.app.thepunjabifeast.helpers

import java.util.Calendar

object DateOfBirthHelper {

    fun minimumAge(): Long {
        return ageConstraints(18)
    }

    fun maximumAge(): Long {
        return ageConstraints(120)
    }

    private fun ageConstraints(age: Int): Long {
        val calendar: Calendar = Calendar.getInstance()
        calendar.add(Calendar.YEAR, -age)
        return calendar.timeInMillis
    }
}
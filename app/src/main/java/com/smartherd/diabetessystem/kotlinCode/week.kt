package com.smartherd.diabetessystem.kotlinCode

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.WeekFields
import java.util.Locale

fun main() {
    // Get the current date
    val currentDate = LocalDate.now()

    // Get the week of the year using WeekFields
    val weekFields = WeekFields.of(Locale.getDefault())
    val weekOfYear = currentDate.get(weekFields.weekOfWeekBasedYear())
    println("Week of the year: $weekOfYear")

    // You can also use LocalDateTime
    val currentDateTime = LocalDateTime.now()
    val weekOfYearDateTime = currentDateTime.get(weekFields.weekOfWeekBasedYear())
    println("Week of the year (DateTime): $weekOfYearDateTime")

    // Format the date using a custom pattern
    val customPattern = "yyyy-MM-dd HH:mm:ss"
    val formattedDateTime = currentDateTime.format(DateTimeFormatter.ofPattern(customPattern))
    println(formattedDateTime)

    // Get the current month as a number
    val currentMonth = java.time.Month.from(java.time.LocalDate.now()).value
    println("Current month (number): $currentMonth")
}

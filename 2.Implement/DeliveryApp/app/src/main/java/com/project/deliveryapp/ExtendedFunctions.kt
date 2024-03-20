package com.project.deliveryapp

import org.json.JSONArray
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.format.DateTimeParseException
import java.time.temporal.ChronoField
import java.time.temporal.ChronoUnit


//Convert JOSNObject to List<T>
inline fun <reified T> JSONArray.toList(): List<T> {
    val result = mutableListOf<T>()

    for(index in 0 until this.length()) {
        val str = this[index].toString()
        val ele = when(T::class) {
            Int::class -> str.toInt() as T
            String::class -> str as T
            Double::class -> str.toDouble() as T
            Float::class -> str.toFloat() as T
            Long::class -> str.toLong() as T
            LocalDateTime::class -> str.toLocalDateTime() as T
            else -> throw IllegalArgumentException()
        }

        result.add(ele)
    }

    return result.toList()
}

//Convert String to LocalDateTime
fun String.toLocalDateTime(): LocalDateTime {
    var str = this.split("T")[0]
    str += "T00:00:00.000"

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")

    return LocalDateTime.parse(str, formatter)
}

fun String.toLocalDate(): LocalDate {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    return LocalDate.parse(this, formatter)
}


package com.project.deliveryapp

import org.json.JSONArray
import java.time.LocalDateTime
import java.time.format.DateTimeFormatterBuilder
import java.time.format.DateTimeParseException
import java.time.temporal.ChronoField


//Convert JOSNObject to List<T>
inline fun <reified T> JSONArray.toList(): List<T> {
    val result = mutableListOf<T>()

    for(index in 0..this.length()) {
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
    try {
        val formatter = DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm[:ss]") // #1
            .appendFraction(ChronoField.MILLI_OF_SECOND, 0, 9, true) // #2
            .toFormatter()

        return LocalDateTime.parse(this, formatter)
    } catch (e: DateTimeParseException) {
        throw IllegalArgumentException()
    }
}


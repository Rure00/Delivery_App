package com.project.deliveryapp

import org.json.JSONArray
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.format.DateTimeParseException
import java.time.temporal.ChronoField


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
    val str = this.replace('T', ' ')
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSS")

    return LocalDateTime.parse(str, formatter)
}


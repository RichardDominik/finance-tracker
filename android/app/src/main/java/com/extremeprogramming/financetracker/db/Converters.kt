package com.extremeprogramming.financetracker.db

import androidx.room.TypeConverter
import org.threeten.bp.LocalDateTime
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

object Converters {
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    @TypeConverter
    @JvmStatic
    fun toDateTime(value: String?): LocalDateTime? {
        return value?.let {
            return formatter.parse(value, LocalDateTime::from)
        }
    }

    @TypeConverter
    @JvmStatic
    fun fromDateTime(date: LocalDateTime?): String? {
        return date?.format(formatter)
    }
}
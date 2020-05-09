package com.extremeprogramming.financetracker.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.extremeprogramming.financetracker.db.Converters
import org.threeten.bp.LocalDateTime
import org.threeten.bp.OffsetDateTime
import java.util.*

@Entity
data class Record (
    @PrimaryKey
    val recordId: Int,

    val description: String?,

    val amount: Double,

    val recordType: Int?,

    val recordCategoryId: Int,

    val date: LocalDateTime

    )
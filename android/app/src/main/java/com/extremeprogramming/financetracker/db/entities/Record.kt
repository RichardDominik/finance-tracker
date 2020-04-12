package com.extremeprogramming.financetracker.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Record (
    @PrimaryKey
    val recordId: Int,

    val description: String?,

    val amount: Double,

    val recordType: Int?,

    val recordCategoryId: Int,

    val date: Date

    )
package com.extremeprogramming.financetracker.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.LocalDateTime

@Entity
data class Record (
    @PrimaryKey
    val recordId: Int,

    val description: String?,

    val amount: Double,

    val recordType: String?,

    val recordCategoryId: Int,

    val date: LocalDateTime

) {
    override fun toString(): String {
        return "description = $description, amount = $amount, recordType = $recordType"
    }
}
package com.extremeprogramming.financetracker.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.LocalDateTime

@Entity
data class Record (
    val description: String?,

    val amount: Double,

    val recordType: Int?,

    val recordCategoryId: Int,

    val date: LocalDateTime

) {
    @PrimaryKey(autoGenerate = true)
    var recordId: Int = 0

    override fun toString(): String {
        return "description = $description, amount = $amount, recordType = $recordType"
    }
}
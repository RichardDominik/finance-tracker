package com.extremeprogramming.financetracker.db.entities

import androidx.room.Embedded
import androidx.room.Relation

data class RecordWithCategory (
    @Embedded val category: Category,
    @Relation (
        parentColumn = "categoryId",
        entityColumn = "recordCategoryId"
    )
    val record: Record

)
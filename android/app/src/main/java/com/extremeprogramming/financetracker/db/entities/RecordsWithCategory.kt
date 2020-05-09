package com.extremeprogramming.financetracker.db.entities

import androidx.room.Embedded
import androidx.room.Relation

data class RecordWithCategory (
    @Embedded val record: Record,
    @Relation (
        parentColumn = "recordCategoryId",
        entityColumn = "categoryId"
    )
    val category: Category

)
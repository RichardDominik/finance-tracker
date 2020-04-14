package com.extremeprogramming.financetracker.db.entities

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithRecords (
    @Embedded val category: Category,
    @Relation(
      parentColumn = "categoryId",
      entityColumn = "recordCategoryId"
    )
    val records: List<Record>
)
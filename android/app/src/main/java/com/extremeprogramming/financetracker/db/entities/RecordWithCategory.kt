package com.extremeprogramming.financetracker.db.entities

import androidx.room.Embedded
import androidx.room.Relation

class RecordWithCategory (
    @Embedded
    val record: Record,

    @Relation(
        parentColumn = "recordCategoryId",
        entityColumn = "categoryId"
    )
    val category: Category?
) {
    override fun toString(): String {
        return "record = ($record), category = ($category)"
    }
}
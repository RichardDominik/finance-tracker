package com.extremeprogramming.financetracker.db.entities

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithRecords (
  @Embedded val category: Category,
  @Relation(
      parentColumn = "categoryId",
      entityColumn = "recordCategoryId",
      entity = Record::class
  )
  val records: List<Record>
)
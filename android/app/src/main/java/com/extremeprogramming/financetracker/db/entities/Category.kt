package com.extremeprogramming.financetracker.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category (
    @PrimaryKey
    val categoryId: Int,

    val name: String,

    val description: String? = null,

    val budget: Double? = null

)
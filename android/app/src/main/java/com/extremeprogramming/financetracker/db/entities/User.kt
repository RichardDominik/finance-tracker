package com.extremeprogramming.financetracker.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.LocalDateTime
import java.util.*

@Entity
data class User(
    @PrimaryKey
    val userId: Int,

    val token: String,
    val date: LocalDateTime
)
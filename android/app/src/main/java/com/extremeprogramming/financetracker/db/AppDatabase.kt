package com.extremeprogramming.financetracker.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.extremeprogramming.financetracker.db.daos.CategoryDao
import com.extremeprogramming.financetracker.db.daos.RecordDao
import com.extremeprogramming.financetracker.db.daos.UserDao
import com.extremeprogramming.financetracker.db.entities.Category
import com.extremeprogramming.financetracker.db.entities.Record
import com.extremeprogramming.financetracker.db.entities.User

@Database(entities = arrayOf(User::class, Category::class, Record::class), version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun categoryDao(): CategoryDao
    abstract fun recordDao(): RecordDao

}
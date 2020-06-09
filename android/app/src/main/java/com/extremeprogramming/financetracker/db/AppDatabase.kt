package com.extremeprogramming.financetracker.db

import android.content.ContentValues
import android.content.Context
import android.util.Log
import androidx.annotation.NonNull
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.extremeprogramming.financetracker.db.daos.CategoryDao
import com.extremeprogramming.financetracker.db.daos.RecordDao
import com.extremeprogramming.financetracker.db.daos.UserDao
import com.extremeprogramming.financetracker.db.entities.Category
import com.extremeprogramming.financetracker.db.entities.Record
import com.extremeprogramming.financetracker.db.entities.User
import java.util.*

@Database(entities = arrayOf(User::class, Category::class, Record::class), version = 4,exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun categoryDao(): CategoryDao
    abstract fun recordDao(): RecordDao


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }

    }


}
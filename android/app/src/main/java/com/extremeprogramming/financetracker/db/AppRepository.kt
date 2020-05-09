package com.extremeprogramming.financetracker.db

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.extremeprogramming.financetracker.db.daos.CategoryDao
import com.extremeprogramming.financetracker.db.daos.RecordDao
import com.extremeprogramming.financetracker.db.daos.UserDao
import com.extremeprogramming.financetracker.db.entities.*
import org.threeten.bp.LocalDateTime
import java.util.*
import kotlin.collections.HashSet

class AppRepository(private val userDao : UserDao,
                    private val categoryDao : CategoryDao,
                    private val recordDao : RecordDao) {

    fun getUser() : LiveData<User> {
        return userDao.getOne()
    }

    fun getCategoriesWithRecords() : LiveData<List<CategoryWithRecords>> {
        return categoryDao.getAllWithRecords()
    }

    fun getCategories() : LiveData<List<Category>> {
        return categoryDao.getAll()
    }

    fun findCategoryById(id: Int): LiveData<Category> {
        return categoryDao.findById(id)
    }

    fun getRecordsByMonth(month : Int) : LiveData<List<RecordWithCategory>> {
        val monthString = month.toString().padStart(2, '0')
        return recordDao.getAllByMonth(monthString)
    }

    fun getLastTenRecords() : LiveData<List<RecordWithCategory>> {
        return recordDao.getLastTenRecords()
    }

    fun getRecordsByDate(date : LocalDateTime) : LiveData<List<RecordWithCategory>> {
        return recordDao.getAllByDate(date)
    }

    fun getAllRecords() : LiveData<List<RecordWithCategory>> {
        return recordDao.getAll()
    }

    suspend fun insert(record : Record) {
        recordDao.insert(record)
    }

    suspend fun insert(category : Category) {
        categoryDao.insert(category)
    }

    suspend fun insert(user : User) {
        userDao.insert(user)
    }

    suspend fun delete(user : User) {
        userDao.delete(user)
    }

    suspend fun delete(category : Category) {
        categoryDao.delete(category)
    }

    suspend fun delete(record : Record) {
        recordDao.delete(record)
    }

    suspend fun update(category : Category) {
        categoryDao.update(category)
    }

    suspend fun update(record : Record) {
        recordDao.update(record)
    }

    suspend fun update(user : User) {
        userDao.update(user)
    }

    suspend fun deleteAll() {
        userDao.deleteAll()
        categoryDao.deleteAll()
        recordDao.deleteAll()
    }

    companion object {
        @Volatile
        private var INSTANCE: AppRepository? = null

        fun getRepository(context: Context): AppRepository {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val userDao = AppDatabase.getDatabase(context).userDao()
                val recordDao = AppDatabase.getDatabase(context).recordDao()
                val categoryDao = AppDatabase.getDatabase(context).categoryDao()

                val instance = AppRepository(userDao,categoryDao,recordDao)
                INSTANCE = instance
                return instance
            }
        }
    }



}
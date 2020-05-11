package com.extremeprogramming.financetracker.db

import android.content.Context
import androidx.lifecycle.LiveData
import com.extremeprogramming.financetracker.db.daos.CategoryDao
import com.extremeprogramming.financetracker.db.daos.RecordDao
import com.extremeprogramming.financetracker.db.daos.UserDao
import com.extremeprogramming.financetracker.db.entities.*
import org.threeten.bp.LocalDateTime
import java.util.*

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

    fun getThisMothsRecordsWithCategory(): LiveData<List<RecordWithCategory>> {
        val thisMonth = Calendar.getInstance().time.month + 1
        return getRecordsWithCategoryByMonth(thisMonth)
    }

    fun getThisMonthsCategoriesWithRecords(): LiveData<List<CategoryWithRecords>> {
        val thisMonth = Calendar.getInstance().time.month + 1
        return getCategoriesWithRecordsByMonth(thisMonth)
    }

    fun getRecordsWithCategoryByMonth(month : Int) : LiveData<List<RecordWithCategory>> {
        val monthString = month.toString().padStart(2, '0')
        return recordDao.getAllByMonth(monthString)
    }

    fun getCategoriesWithRecordsByMonth(month: Int): LiveData<List<CategoryWithRecords>> {
        val monthString = month.toString().padStart(2, '0')
        return categoryDao.getAllWithRecords()
    }

    fun getLastTenRecordsWithCategory() : LiveData<List<RecordWithCategory>> {
        return recordDao.getLastTenRecords()
    }

    fun getRecordsWithCategoryByDate(date : LocalDateTime) : LiveData<List<RecordWithCategory>> {
        return recordDao.getAllByDate(date)
    }

    fun getAllRecordsWithCategory() : LiveData<List<RecordWithCategory>> {
        return recordDao.getAllRecordsWithCategory()
    }

    fun getAllRecords(): LiveData<List<Record>> {
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
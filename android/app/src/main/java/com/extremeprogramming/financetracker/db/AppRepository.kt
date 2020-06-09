package com.extremeprogramming.financetracker.db

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.extremeprogramming.financetracker.backEndConnection.BackendEndPoints
import com.extremeprogramming.financetracker.backEndConnection.JsonToCategory
import com.extremeprogramming.financetracker.backEndConnection.ServiceBuilder
import com.extremeprogramming.financetracker.db.daos.CategoryDao
import com.extremeprogramming.financetracker.db.daos.RecordDao
import com.extremeprogramming.financetracker.db.daos.UserDao
import com.extremeprogramming.financetracker.db.entities.*
import kotlinx.coroutines.*
import org.threeten.bp.LocalDateTime
import java.util.*
import com.extremeprogramming.financetracker.backEndConnection.JsonToRecord


class AppRepository(
    private val userDao: UserDao,
    private val categoryDao: CategoryDao,
    private val recordDao: RecordDao
) {

    private val categories = MutableLiveData<List<Category>>()
    private val records = MutableLiveData<List<Record>>()
    private val service = ServiceBuilder.buildService(BackendEndPoints::class.java)

    fun getUser(): LiveData<User> {
        return userDao.getOne()
    }

    fun getCategoriesWithRecords(): LiveData<List<CategoryWithRecords>> {
        return categoryDao.getAllWithRecords()
    }

    suspend fun getCategories(): LiveData<List<Category>> {
        //val categories =  MutableLiveData<List<Category>>()
        coroutineScope {
            launch {
                if (categoryDao.getCount() == 0) {
                    val serverCategories = getCategoriesFromServer()
                    insertAllCategories(serverCategories)
                }

                val localCategories = getCategoriesFromLocal()
                categories.postValue(localCategories)
            }
        }
        return categories

    }

    private suspend fun getCategoriesFromServer(): List<Category>? {
        return CoroutineScope(Dispatchers.IO).async {
            val response = service.getCategories().execute()
            var serverCategories: List<Category>? = null

            if (response.isSuccessful) {
                serverCategories = JsonToCategory.toCategories(response.body()?.string())
            }
            return@async serverCategories
        }.await()
    }

    private suspend fun getCategoriesFromLocal(): List<Category>? {
        return CoroutineScope(Dispatchers.IO).async {

            val localCategories = categoryDao.getAll()

            return@async localCategories?.value
        }.await()
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

    fun getRecordsWithCategoryByMonth(month: Int): LiveData<List<RecordWithCategory>> {
        val monthString = month.toString().padStart(2, '0')
        return recordDao.getAllByMonth(monthString)
    }

    fun getCategoriesWithRecordsByMonth(month: Int): LiveData<List<CategoryWithRecords>> {
        val monthString = month.toString().padStart(2, '0')
        return categoryDao.getAllWithRecords()
    }

    fun getLastTenRecordsWithCategory(): LiveData<List<RecordWithCategory>> {
        return recordDao.getLastTenRecords()
    }

    fun getRecordsWithCategoryByDate(date: LocalDateTime): LiveData<List<RecordWithCategory>> {
        return recordDao.getAllByDate(date)
    }

    fun getAllRecordsWithCategory(): LiveData<List<RecordWithCategory>> {
        return recordDao.getAllRecordsWithCategory()
    }

    fun getAllRecords(): LiveData<List<Record>> {
        GlobalScope.launch {

            if (recordDao.getCount() == 0) {
                val serverRecords = getRecordsFromServer()
                insertAllRecords(serverRecords)
            }

            val localRecords = getRecordsFromLocal()
            records.postValue(localRecords)

        }
        return records
    }


    private suspend fun getRecordsFromServer(): List<Record>? {
        return CoroutineScope(Dispatchers.IO).async {
            val response = service.getRecords().execute()
            var serverRecords: List<Record>? = null

            if (response.isSuccessful) {
                serverRecords = JsonToRecord.toRecords(response.body()?.string())
            }
            return@async serverRecords
        }.await()
    }

    private suspend fun getRecordsFromLocal(): List<Record>? {
        return CoroutineScope(Dispatchers.IO).async {

            val localRecords = recordDao.getAll()

            return@async localRecords?.value
        }.await()
    }


    suspend fun insertAllCategories(categories: List<Category>?) {
        categories?.forEach {
            categoryDao.insert(it)
        }
    }

    suspend fun insertAllRecords(records: List<Record>?) {
        records?.forEach {
            recordDao.insert(it)
        }
    }

    suspend fun insert(record: Record) {
        recordDao.insert(record)
    }

    suspend fun insert(category: Category) {
        categoryDao.insert(category)
    }

    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    suspend fun delete(user: User) {
        userDao.delete(user)
    }

    suspend fun delete(category: Category) {
        categoryDao.delete(category)
    }

    suspend fun delete(record: Record) {
        recordDao.delete(record)
    }

    suspend fun update(category: Category) {
        categoryDao.update(category)
    }

    suspend fun update(record: Record) {
        recordDao.update(record)
    }

    suspend fun update(user: User) {
        userDao.update(user)
    }

    suspend fun deleteAll() {
        userDao.deleteAll()
        categoryDao.deleteAll()
        recordDao.deleteAll()
    }

    suspend fun refreshDataFromServer() {
        coroutineScope {
            val serverCategories = getCategoriesFromServer()
            val serverRecords = getRecordsFromServer()
            if (serverCategories == null || serverRecords == null) {
                return@coroutineScope
            }

            deleteAll()


        }
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

                val instance = AppRepository(userDao, categoryDao, recordDao)
                INSTANCE = instance
                return instance
            }
        }
    }


}
package com.extremeprogramming.financetracker.db.daos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.extremeprogramming.financetracker.db.entities.Category
import com.extremeprogramming.financetracker.db.entities.CategoryWithRecords
import java.util.*

@Dao
interface CategoryDao {
    @Query("SELECT * FROM Category")
    fun getAll() : LiveData<List<Category>>

    @Query("SELECT * FROM Category WHERE categoryId = :categoryId")
    fun findById(categoryId :Int) : LiveData<Category>

    @Transaction
    @Query("SELECT * FROM Category")
    fun getAllWithRecords() : LiveData<List<CategoryWithRecords>>

    @Query("DELETE FROM Category")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(category : Category)

    @Delete
    suspend fun delete(category : Category)

    @Update
    suspend fun update(category : Category)

    @Query("select count(*) from Category")
    fun getCount() : Int

}
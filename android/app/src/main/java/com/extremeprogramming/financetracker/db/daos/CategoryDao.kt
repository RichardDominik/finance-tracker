package com.extremeprogramming.financetracker.db.daos

import androidx.room.*
import com.extremeprogramming.financetracker.db.entities.Category
import com.extremeprogramming.financetracker.db.entities.CategoryWithRecords

@Dao
interface CategoryDao {
    @Query("SELECT * FROM Category")
    fun getAll() : List<Category>

    @Query("SELECT * FROM Category WHERE categoryId = :categoryId")
    fun findById(categoryId :Int) : Category

    @Transaction
    @Query("SELECT * FROM Category")
    fun getAllWithRecords() : List<CategoryWithRecords>

    @Query("DELETE FROM Category")
    fun deleteAll()

    @Insert
    fun insert(category : Category)

    @Delete
    fun delete(category : Category)

    @Update
    fun update(category : Category)


}
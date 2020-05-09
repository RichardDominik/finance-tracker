package com.extremeprogramming.financetracker.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.extremeprogramming.financetracker.db.entities.User

/**
 * Only signed user will be in the table
 */
@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getOne(): LiveData<User>

    @Query("DELETE FROM user")
    suspend fun deleteAll()

    @Insert
    suspend fun insert(user: User)

    @Delete
    suspend fun delete(user: User)

    @Update
    suspend fun update(user: User)

}
package com.extremeprogramming.financetracker.db.daos

import androidx.room.*
import com.extremeprogramming.financetracker.db.entities.User

/**
 * Only signed user will be in the table
 */
@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getOne(): User

    @Query("DELETE FROM user")
    fun deleteAll()

    @Insert
    fun insert(user: User)

    @Delete
    fun delete(user: User)

    @Update
    fun update(user: User)

}
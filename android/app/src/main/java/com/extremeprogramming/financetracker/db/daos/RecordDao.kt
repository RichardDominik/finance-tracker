package com.extremeprogramming.financetracker.db.daos

import androidx.room.*
import com.extremeprogramming.financetracker.db.entities.Record
import com.extremeprogramming.financetracker.db.entities.RecordWithCategory
import java.util.*

@Dao
interface RecordDao {
    @Query("DELETE FROM Record")
    fun deleteAll()

    @Update
    fun update(record: Record)

    @Delete
    fun delete(record: Record)

    @Insert
    fun insert(record: Record)


}
package com.extremeprogramming.financetracker.db.daos

import androidx.room.*
import com.extremeprogramming.financetracker.db.entities.Record
import com.extremeprogramming.financetracker.db.entities.RecordWithCategory
import java.util.*

@Dao
interface RecordDao {
    @Transaction
    @Query("SELECT * FROM Record")
    fun getAll() : List<RecordWithCategory>

    @Transaction
    @Query("SELECT * FROM Record WHERE dstrftime('%m', date) = :month")
    fun getAllByMonth(month:Int) : List<RecordWithCategory>

    @Transaction
    @Query("SELECT * FROM Record ORDER BY date DESC LIMIT 10")
    fun getLastTenRecords() : List<RecordWithCategory>

    @Transaction
    @Query("SELECT * FROM Record WHERE date = :date")
    fun getAllByDate(date: Date) : List<RecordWithCategory>

    @Query("DELETE FROM Record")
    fun deleteAll()

    @Update
    fun update(record: Record)

    @Delete
    fun delete(record: Record)

    @Insert
    fun insert(record: Record)


}
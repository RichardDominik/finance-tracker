package com.extremeprogramming.financetracker.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.extremeprogramming.financetracker.db.entities.Record
import com.extremeprogramming.financetracker.db.entities.RecordWithCategory
import org.threeten.bp.LocalDateTime
import java.util.*

@Dao
interface RecordDao {

    @Transaction
    @Query("SELECT * FROM Record")
    fun getAllRecordsWithCategory() : LiveData<List<RecordWithCategory>>

    @Query("SELECT * FROM Record")
    fun getAll() : LiveData<List<Record>>

    @Query("select count(*) from Record")
    fun getCount() : Int

    @Transaction
    @Query("SELECT * FROM Record WHERE strftime('%m', date) IN (:month)")
    fun getAllByMonth(month: String) : LiveData<List<RecordWithCategory>>

    @Transaction
    @Query("SELECT * FROM Record ORDER BY datetime(date) DESC LIMIT 10")
    fun getLastTenRecords() : LiveData<List<RecordWithCategory>>

    @Transaction
    @Query("SELECT * FROM Record WHERE date = :date")
    fun getAllByDate(date: LocalDateTime) : LiveData<List<RecordWithCategory>>

    @Query("DELETE FROM Record")
    suspend fun deleteAll()

    @Update
    suspend fun update(record: Record)

    @Delete
    suspend fun delete(record: Record)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(record: Record)


}
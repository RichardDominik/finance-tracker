package com.extremeprogramming.financetracker.db

import android.content.ContentValues
import android.content.Context
import android.util.Log
import androidx.room.OnConflictStrategy
import androidx.room.Room
import com.extremeprogramming.financetracker.db.entities.Category
import com.extremeprogramming.financetracker.db.entities.Record
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDateTime

class Generate {

    companion object {
        suspend fun generate(context : Context) =  withContext(Dispatchers.IO) {
            //generate some data for creating charts

            Log.d("DEBUGGING","populating database: START")
            val repository = AppRepository.getRepository(context)

            repository.deleteAll()

            for ((index,categoryName) in listOf("travel","food","fun","hobby","clothes").withIndex()) {
                val category = Category(index,categoryName,null,null)

                repository.insert(category)

            }

            val amounts : List<Double> = listOf(20.5,30.0,55.0,65.5,12.0,5.5,75.0,6.5,7.0)
            for ((index,recordAmount) in amounts.withIndex()) {
                val date : LocalDateTime = LocalDateTime.of(2020,5,index+1,0,0)
                val record = Record(index,null,recordAmount,null,(0..4).random(),date)

                repository.insert(record)
            }

            Log.d("DEBUGGING","populating database: END")

        }
    }

}
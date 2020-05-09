package com.extremeprogramming.financetracker.ui.reports

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.extremeprogramming.financetracker.db.AppRepository
import com.extremeprogramming.financetracker.db.entities.CategoryWithRecords
import com.extremeprogramming.financetracker.db.entities.RecordWithCategory
import java.util.*

class ReportsViewModel(application : Application) : AndroidViewModel(application) {

    private val currentMonth : Int = java.time.LocalDateTime.now().monthValue

    private val repository : AppRepository = AppRepository.getRepository(application)

    private val _monthSpendingsByCategory = repository.getRecordsByMonth(currentMonth)
    val monthSpendingsByCategory: LiveData<List<RecordWithCategory>> = _monthSpendingsByCategory

    private val _spendingsByMonth = repository.getRecordsByMonth(currentMonth)
    val spendingsByMonth: LiveData<List<RecordWithCategory>> = _spendingsByMonth




}
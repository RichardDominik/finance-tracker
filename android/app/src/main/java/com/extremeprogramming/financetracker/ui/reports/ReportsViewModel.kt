package com.extremeprogramming.financetracker.ui.reports

import android.app.Application
import androidx.lifecycle.*
import com.extremeprogramming.financetracker.db.AppRepository
import com.extremeprogramming.financetracker.db.entities.RecordWithCategory

class ReportsViewModel(application : Application) : AndroidViewModel(application) {
    private val repository : AppRepository = AppRepository.getRepository(application)
    val monthSpendingsByCategory: LiveData<List<RecordWithCategory>> = repository.getThisMothsRecordsWithCategory()
    val spendingsByMonth: LiveData<List<RecordWithCategory>> = repository.getThisMothsRecordsWithCategory()
}
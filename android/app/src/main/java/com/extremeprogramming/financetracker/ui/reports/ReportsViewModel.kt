package com.extremeprogramming.financetracker.ui.reports

import android.app.Application
import androidx.lifecycle.*
import com.extremeprogramming.financetracker.db.AppRepository
import com.extremeprogramming.financetracker.db.entities.RecordWithCategory

class ReportsViewModel(private val appRepository: AppRepository) : ViewModel() {
    val monthSpendingsByCategory: LiveData<List<RecordWithCategory>> = appRepository.getThisMothsRecordsWithCategory()
    val spendingsByMonth: LiveData<List<RecordWithCategory>> = appRepository.getThisMothsRecordsWithCategory()
}
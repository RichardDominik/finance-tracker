package com.extremeprogramming.financetracker.ui.home

import androidx.lifecycle.*
import com.extremeprogramming.financetracker.db.AppRepository
import com.extremeprogramming.financetracker.db.entities.CategoryWithRecords
import com.extremeprogramming.financetracker.db.entities.RecordWithCategory

class HomeViewModel(private val appRepository: AppRepository) : ViewModel() {
    val balance: LiveData<Double?> = Transformations.map(appRepository.getAllRecords()) { it ->
        it.sumByDouble { it.amount }
    }

    val monthBalance: LiveData<Double?> = Transformations.map(appRepository.getThisMonthsCategoriesWithRecords()) { it ->
        if (it.isEmpty()) {
            0.0
        } else {
            it.sumByDouble { it.records.sumByDouble { it.amount } }
        }
    }

    val monthSpending: LiveData<List<CategoryWithRecords>> = appRepository.getThisMonthsCategoriesWithRecords()

    val recentTransactions: LiveData<List<RecordWithCategory>> = appRepository.getLastTenRecordsWithCategory()

}
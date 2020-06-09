package com.extremeprogramming.financetracker.ui.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.extremeprogramming.financetracker.db.AppRepository
import com.extremeprogramming.financetracker.ui.home.HomeViewModel
import com.extremeprogramming.financetracker.ui.reports.ReportsViewModel

class ReportsViewModelFactory(
    private val application: Application,
    private val appRepository: AppRepository
): ViewModelProvider.AndroidViewModelFactory(application) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ReportsViewModel(appRepository) as T
    }
}
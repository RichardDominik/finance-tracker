package com.extremeprogramming.financetracker.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.extremeprogramming.financetracker.db.entities.Category
import com.extremeprogramming.financetracker.db.entities.Record
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class HomeViewModel : ViewModel() {
    private val _balance = MutableLiveData<Double>().apply { value = 123.32 }
    val balance: LiveData<Double> = _balance

    private val _monthBalance = MutableLiveData<Double>().apply { value = -123.32 }
    val monthBalance: LiveData<Double> = _monthBalance

    private val _monthSpending = MutableLiveData<List<Category>>()
    val monthSpending: LiveData<List<Category>> = _monthSpending

    private val _recentTransactions = MutableLiveData<List<Record>>()
    val recentTransactions: LiveData<List<Record>> = _recentTransactions

    init {
        loadDummyCategories()
        loadDummyRecords()
    }

    private fun loadDummyCategories() {
        viewModelScope.launch {
            val categories = ArrayList<Category>()
            repeat((5..12).random()) {
                delay(100)
                categories.add(Category(it, it.toString(), budget = Random.nextDouble() * 182))
            }

            _monthSpending.postValue(categories)
        }
    }

    private fun loadDummyRecords() {
        viewModelScope.launch {
            val records = ArrayList<Record>()
            repeat(5) {
                delay(100)
                records.add(Record(it, it.toString(), Random.nextDouble() * 123, null, 1, LocalDateTime.now()))
            }

            _recentTransactions.postValue(records)
        }
    }
}
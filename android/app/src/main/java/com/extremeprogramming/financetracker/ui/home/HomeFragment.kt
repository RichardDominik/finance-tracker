package com.extremeprogramming.financetracker.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.extremeprogramming.financetracker.R
import com.extremeprogramming.financetracker.db.entities.Category
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import kotlin.random.Random

class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var pieChart: PieChart

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d("WAT","HOME")
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        pieChart = root.findViewById(R.id.pie_char_month_categories)

        val balanceTextView = root.findViewById<TextView>(R.id.text_view_balance_value)
        homeViewModel.balance.observe(this, Observer {
            balanceTextView.text = it.toString()
        })

        val monthBalanceTextView = root.findViewById<TextView>(R.id.text_view_month_balance_value)
        homeViewModel.monthBalance.observe(this, Observer {
            monthBalanceTextView.text = it.toString()
        })

        homeViewModel.monthSpending.observe(this, Observer {
            updatePieChart(it)
        })

        return root
    }

    private fun updatePieChart(categories: List<Category>) {
        val values = ArrayList<PieEntry>()
        for (category in categories) {
            values.add(PieEntry(category.budget!!.toFloat(), category.name))
        }

        val dataSet = PieDataSet(values, "")
        val data = PieData(dataSet)
        pieChart.data = data
        pieChart.invalidate()
    }
}
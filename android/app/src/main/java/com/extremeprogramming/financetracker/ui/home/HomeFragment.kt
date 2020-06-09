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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.extremeprogramming.financetracker.R
import com.extremeprogramming.financetracker.db.entities.CategoryWithRecords
import com.extremeprogramming.financetracker.ui.Colors
import com.extremeprogramming.financetracker.ui.tools.InjectorUtils
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModels {
        InjectorUtils.provideHomeViewModelFactory(requireActivity().application)
    }
    private lateinit var pieChart: PieChart
    private lateinit var recentActivityAdapter: RecentActivityAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d("WAT","HOME")
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        pieChart = root.findViewById(R.id.pie_char_month_categories)

        recentActivityAdapter = RecentActivityAdapter(listOf())

        root.findViewById<RecyclerView>(R.id.recycler_view_recent_activity).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(false)
            // use a linear layout manager
            layoutManager = LinearLayoutManager(requireContext())

            // specify an viewAdapter (see also next example)
            adapter = recentActivityAdapter
        }

        val balanceTextView = root.findViewById<TextView>(R.id.text_view_balance_value)
        homeViewModel.balance.observe(viewLifecycleOwner, Observer {
            if (it == null) {
                balanceTextView.text = "--"
            } else {
                balanceTextView.text = "%.2f".format(it)
            }
        })

        val monthBalanceTextView = root.findViewById<TextView>(R.id.text_view_month_balance_value)
        homeViewModel.monthBalance.observe(viewLifecycleOwner, Observer {
            if (it == null) {
                monthBalanceTextView.text = "--"
            } else {
                monthBalanceTextView.text = "%.2f".format(it)
            }
        })

        homeViewModel.monthSpending.observe(viewLifecycleOwner, Observer {
            updatePieChart(it)
        })

        homeViewModel.recentTransactions.observe(viewLifecycleOwner, Observer {
            recentActivityAdapter.recordsWithCategory = it
            recentActivityAdapter.notifyDataSetChanged()
        })

        return root
    }

    private fun updatePieChart(categoriesWithRecords: List<CategoryWithRecords>) {
        val values = ArrayList<PieEntry>()
        for (categoryWithRecord in categoriesWithRecords) {
            if (categoryWithRecord.records.isNotEmpty()) {
                values.add(PieEntry(categoryWithRecord.records.sumByDouble { it.amount }.toFloat(), categoryWithRecord.category.name))
            }
        }

        val dataSet = PieDataSet(values, "")
        dataSet.colors = Colors.getColors()
        val data = PieData(dataSet)
        pieChart.data = data
        pieChart.invalidate()
    }
}
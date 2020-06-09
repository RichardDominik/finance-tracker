package com.extremeprogramming.financetracker.ui.reports

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.extremeprogramming.financetracker.db.Converters
import com.extremeprogramming.financetracker.db.entities.Category
import com.extremeprogramming.financetracker.db.entities.CategoryWithRecords
import com.extremeprogramming.financetracker.db.entities.Record
import com.extremeprogramming.financetracker.db.entities.RecordWithCategory
import com.extremeprogramming.financetracker.ui.Colors
import com.extremeprogramming.financetracker.ui.tools.InjectorUtils
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.components.YAxis.YAxisLabelPosition
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.interfaces.datasets.IDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import org.threeten.bp.LocalDateTime
import java.math.BigDecimal
import java.util.function.BiFunction


class ReportsFragment : Fragment() {

    private val reportsViewModel: ReportsViewModel by viewModels() {
        InjectorUtils.provideReportsViewModelFactory(requireActivity().application)
    }

    //colors
    private val colors: ArrayList<Int> = Colors.getColors()

    //charts
    private lateinit var monthSpendingByCategoryChart: PieChart
    private lateinit var spendingsByMonthChart: BarChart

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(
            com.extremeprogramming.financetracker.R.layout.fragment_reports,
            container,
            false
        )

        initMonthSpendingByCategoryChart(root)
        initSpendingsByMonthChart(root)

        reportsViewModel.monthSpendingsByCategory.observe(viewLifecycleOwner, Observer {
            updateMonthSpendingsByCategory(it)
        })

        reportsViewModel.spendingsByMonth.observe(viewLifecycleOwner, Observer {
            updateSpendingsByMonth(it)
        })

        return root
    }

    private fun initMonthSpendingByCategoryChart(root: View) {

        monthSpendingByCategoryChart =
            root.findViewById(com.extremeprogramming.financetracker.R.id.month_spending_by_category)
        monthSpendingByCategoryChart.setUsePercentValues(true)
        monthSpendingByCategoryChart.description.isEnabled = false
        monthSpendingByCategoryChart.animateY(1400, Easing.EaseInOutQuad)
        monthSpendingByCategoryChart.setExtraOffsets(5F, 5F, 5F, 5F)
        monthSpendingByCategoryChart.setDrawEntryLabels(false)

        monthSpendingByCategoryChart.setEntryLabelColor(Color.WHITE)
        monthSpendingByCategoryChart.setEntryLabelTextSize(12f)

        val l = monthSpendingByCategoryChart.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(false)
        l.xEntrySpace = 7f
        l.yEntrySpace = 0f
        l.yOffset = 0f
    }

    private fun initSpendingsByMonthChart(root: View) {
        spendingsByMonthChart =
            root.findViewById(com.extremeprogramming.financetracker.R.id.spendings_by_month)
        spendingsByMonthChart.description.isEnabled = false
        spendingsByMonthChart.setMaxVisibleValueCount(60)

        spendingsByMonthChart.animateY(1400, Easing.EaseInOutQuad)

        spendingsByMonthChart.setPinchZoom(false)
        spendingsByMonthChart.setDrawGridBackground(false)
        spendingsByMonthChart.setDrawBarShadow(false)


        val xAxis = spendingsByMonthChart.xAxis
        xAxis.position = XAxisPosition.BOTTOM
        xAxis.typeface = Typeface.SANS_SERIF
        xAxis.setDrawGridLines(false)
        xAxis.granularity = 1f // only intervals of 1 day
        xAxis.labelCount = 7

        val priceFormatter = ChartValueFormatter("€")

        for (axis in listOf(spendingsByMonthChart.axisRight, spendingsByMonthChart.axisLeft)) {
            axis.typeface = Typeface.SANS_SERIF
            axis.setLabelCount(8, false)
            axis.valueFormatter = priceFormatter
            axis.setPosition(YAxisLabelPosition.OUTSIDE_CHART)
            axis.spaceTop = 15f
            axis.axisMinimum = 0f
        }

    }

    private fun updateMonthSpendingsByCategory(categoriesWithRecords: List<RecordWithCategory>) {
        val categoryWithRecordsMap = mutableMapOf<Category?, MutableList<Record?>>()
        for (entry in categoriesWithRecords) {
            categoryWithRecordsMap.merge(
                entry.category, mutableListOf(entry.record)
            ) { t, u -> (t + u).toMutableList() }

        }

        val values = ArrayList<PieEntry>()
        for ((category,records) in categoryWithRecordsMap) {
            val recordAmountSum: Double = records.sumByDouble { record -> record?.amount ?: 0.0 }
            values.add(PieEntry(recordAmountSum.toFloat(), category?.name))
        }

        val dataSet = PieDataSet(values, "")
        setColorsToDataSet(dataSet)

        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter(monthSpendingByCategoryChart))
        setTextStyleToData(data)

        monthSpendingByCategoryChart.data = data
        monthSpendingByCategoryChart.invalidate()
    }

    private fun updateSpendingsByMonth(recordsWithCategory: List<RecordWithCategory>) {
        val values = ArrayList<BarEntry>()
        for (recordWithCategory in recordsWithCategory) {
            values.add(
                BarEntry(
                    recordWithCategory.record?.date?.dayOfMonth?.toFloat() ?: 0f,
                    recordWithCategory.record?.amount?.toFloat() ?: 0f
                )
            )
        }

        val dataSet: BarDataSet

        if (spendingsByMonthChart.data != null &&
            spendingsByMonthChart.data.dataSetCount > 0
        ) {
            dataSet = spendingsByMonthChart.data.getDataSetByIndex(0) as BarDataSet
            dataSet.values = values
            spendingsByMonthChart.data.notifyDataChanged()
            spendingsByMonthChart.notifyDataSetChanged()
        } else {
            dataSet = BarDataSet(values, "")
            setColorsToDataSet(dataSet)

            dataSet.setDrawValues(true)

            val dataSets: ArrayList<IBarDataSet> = ArrayList()
            dataSets.add(dataSet)

            val data = BarData(dataSets)
            data.barWidth = 0.9f
            spendingsByMonthChart.data = data
        }

        spendingsByMonthChart.invalidate()
    }

    private fun <T : Entry?> setColorsToDataSet(dataSet: DataSet<T>) {
        dataSet.colors = colors
    }

    private fun <T : IDataSet<out Entry>?> setTextStyleToData(data: ChartData<T>) {
        data.setValueTextColor(Color.WHITE)
        data.setValueTextSize(12f)
        data.setValueTypeface(Typeface.SANS_SERIF)
    }

}
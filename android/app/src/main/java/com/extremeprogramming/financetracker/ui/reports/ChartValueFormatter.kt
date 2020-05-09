package com.extremeprogramming.financetracker.ui.reports

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.DecimalFormat

class ChartValueFormatter(private val suffix: String) : ValueFormatter() {

    private val mFormat: DecimalFormat = DecimalFormat("###,###,###,##0.0")


    override fun getFormattedValue(value: Float): String? {
        return mFormat.format(value.toDouble()) + suffix
    }

    override fun getAxisLabel(value: Float, axis: AxisBase?): String? {
        return when {
            axis is XAxis -> {
                mFormat.format(value.toDouble())
            }
            value > 0 -> {
                mFormat.format(value.toDouble()) + suffix
            }
            else -> {
                mFormat.format(value.toDouble())
            }
        }
    }
}
package com.extremeprogramming.financetracker.ui.home

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.extremeprogramming.financetracker.R
import com.extremeprogramming.financetracker.db.entities.RecordWithCategory

class RecentActivityAdapter(var recordsWithCategory: List<RecordWithCategory>):
    RecyclerView.Adapter<RecentActivityAdapter.RecordViewHolder>()
{
    class RecordViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val descriptionTextView: TextView = view.findViewById(R.id.text_view_description)
        private val categoryNameTextView: TextView = view.findViewById(R.id.text_view_category_name)
        private val topBorderView: View = view.findViewById(R.id.view_border_top)
        private val amountTextView: TextView = view.findViewById(R.id.text_view_amount)

        fun updateRecord(recordWithCategory: RecordWithCategory, isFirst: Boolean) {
            descriptionTextView.text = recordWithCategory.record?.description ?: ""
            categoryNameTextView.text = recordWithCategory.category?.name ?: ""
            val amount = recordWithCategory.record?.amount ?: 0.0

            if (isFirst) {
                topBorderView.visibility = View.VISIBLE
            } else {
                topBorderView.visibility = View.INVISIBLE
            }

            if (amount > 0) {
                amountTextView.setTextColor(Color.parseColor("#2ecc71"))
            } else {
                amountTextView.setTextColor(Color.parseColor("#e74c3c"))
            }
            amountTextView.text = "%.2f €".format(amount)
        }
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        holder.updateRecord(recordsWithCategory[position], position == 0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        return RecordViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.record_view_holder, parent, false))
    }

    override fun getItemCount() = recordsWithCategory.size
}
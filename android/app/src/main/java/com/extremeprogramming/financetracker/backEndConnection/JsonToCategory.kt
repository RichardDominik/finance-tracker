package com.extremeprogramming.financetracker.backEndConnection

import com.extremeprogramming.financetracker.db.entities.Category
import com.extremeprogramming.financetracker.db.entities.Record
import org.json.JSONArray
import org.json.JSONObject
import org.threeten.bp.LocalDateTime

object JsonToCategory {

    @JvmStatic
    fun toCategories(input: String?) : List<Category> {
        val categories = mutableListOf<Category>()

        val profileFileUploadResponse = JSONArray(input)
        for (i in 0 until profileFileUploadResponse.length()) {
            val item = profileFileUploadResponse.getJSONObject(i)
            categories.add(getCategory(item))

        }

        return categories
    }

    @JvmStatic
    fun getCategory(item : JSONObject) : Category {

        val id = item.getInt("id")
        val name = item.getString("name")
        val description = item.getString("description")
        val budget = item.getDouble("budget")

        return Category(id,name,description,budget)
    }
}
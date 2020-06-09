package com.extremeprogramming.financetracker.backEndConnection

import android.util.Log
import com.extremeprogramming.financetracker.db.entities.Record
import com.google.gson.JsonObject
import org.json.JSONArray
import org.json.JSONObject
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime

object JsonToRecord {

    @JvmStatic
    fun toRecords(input: String?) : List<Record> {
        val records = mutableListOf<Record>()

        val profileFileUploadResponse = JSONArray(input)
        for (i in 0 until profileFileUploadResponse.length()) {
            val item = profileFileUploadResponse.getJSONObject(i)
            records.add(getRecord(item))

        }

        return records
    }

    @JvmStatic
    fun getRecord(item : JSONObject) : Record {

        val id = item.getInt("id")
        val type = item.getString("type")
        val description = item.getString("description")
        val amount = item.getDouble("amount")
        val categoryid = item.getJSONObject("category").getInt("id")

        return  Record(id,description,amount,type,categoryid,LocalDateTime.now())
    }
}
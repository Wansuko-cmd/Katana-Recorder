package com.wsr.katanarecorder.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object MutableMapConverter {

    @JvmStatic
    @TypeConverter
    fun toMutableMap(value: String): MutableMap<String, String> {
        val mapType = object : TypeToken<MutableMap<String, String>>() {}.type
        return Gson().fromJson(value, mapType)
    }

    @JvmStatic
    @TypeConverter
    fun fromMutableMap(mutableMap: MutableMap<String, String>): String {
        val gson = Gson()
        return gson.toJson(mutableMap)
    }
}
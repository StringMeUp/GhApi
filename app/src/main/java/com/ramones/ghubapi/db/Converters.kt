package com.ramones.ghubapi.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ramones.ghubapi.db.dbmodels.Repository

class Converters {
    @TypeConverter
    fun fromOwner(value: Repository.Owner?): String? {
        val gson = Gson()
        val type = object : TypeToken<Repository.Owner>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toOwner(value: String?): Repository.Owner? {
        val gson = Gson()
        val type = object : TypeToken<Repository.Owner>() {}.type
        return gson.fromJson(value, type)
    }
}
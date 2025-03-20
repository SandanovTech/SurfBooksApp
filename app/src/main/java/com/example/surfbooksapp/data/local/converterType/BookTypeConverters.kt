package com.example.surfbooksapp.data.local.converterType

import androidx.room.TypeConverter
import com.example.surfbooksapp.data.local.model.ImageLinksLocal
import com.example.surfbooksapp.data.local.model.VolumeInfoLocal
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object BookTypeConverters {
    private val gson = Gson()

    @TypeConverter
    fun fromVolumeInfo(volumeInfoLocal: VolumeInfoLocal?): String? {
        return gson.toJson(volumeInfoLocal)
    }

    @TypeConverter
    fun toVolumeInfo(json: String?): VolumeInfoLocal? {
        return gson.fromJson(json, object : TypeToken<VolumeInfoLocal>() {}.type)
    }

    @TypeConverter
    fun fromImageLinks(imageLinksLocal: ImageLinksLocal?): String? {
        return gson.toJson(imageLinksLocal)
    }

    @TypeConverter
    fun toImageLinks(json: String?): ImageLinksLocal? {
        return gson.fromJson(json, object : TypeToken<ImageLinksLocal>() {}.type)
    }

    @TypeConverter
    fun fromStringList(list: List<String?>?): String? {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toStringList(json: String?): List<String?>? {
        return gson.fromJson(json, object : TypeToken<List<String?>>() {}.type)
    }
}
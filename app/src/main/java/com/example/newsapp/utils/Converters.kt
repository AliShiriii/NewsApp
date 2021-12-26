package com.example.newsapp.utils

import androidx.room.TypeConverter
import com.example.newsapp.model.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source): String = source.name


    @TypeConverter
    fun fromSource(name: String): Source = Source(name, name)
}
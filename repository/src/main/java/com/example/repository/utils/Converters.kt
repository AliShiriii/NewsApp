package com.example.repository.utils

import androidx.room.TypeConverter
import com.example.repository.model.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source): String = source.name


    @TypeConverter
    fun fromSource(name: String): Source = Source(name, name)
}
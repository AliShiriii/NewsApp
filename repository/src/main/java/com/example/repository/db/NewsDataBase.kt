package com.example.repository.db

import androidx.room.Database
import androidx.room.TypeConverters
import com.example.repository.model.Article
import com.example.repository.utils.Converters

@Database(entities = [Article::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NewsDataBase {

    abstract fun getDao() : NewsDao


}
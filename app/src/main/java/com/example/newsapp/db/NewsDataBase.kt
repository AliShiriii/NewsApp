package com.example.newsapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.utils.Converters
import com.example.repository.model.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NewsDataBase : RoomDatabase(){

    abstract fun getDao() : NewsDao

}
package com.example.repository.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.repository.model.Article

@Dao
interface NewsDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: Article): Long

    @Query("SELECT * FROM articles ")
    fun getAllArticle(): LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)


}
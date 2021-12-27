package com.example.newsapp.repository

import com.example.newsapp.api.NewsApi
import com.example.newsapp.db.NewsDao
import com.example.newsapp.model.Article
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsApi: NewsApi, private val newsDao: NewsDao) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) = newsApi.getBreakingNews(countryCode, pageNumber)
    suspend fun searchNews(searchQuery: String, pageNumber: Int) = newsApi.searchForNews(searchQuery, pageNumber)

    suspend fun insertArticle(article: Article) = newsDao.insertArticle(article)

    fun getSavedArticle() = newsDao.getAllArticle()

    suspend fun deleteArticle(article: Article) = newsDao.deleteArticle(article)
}
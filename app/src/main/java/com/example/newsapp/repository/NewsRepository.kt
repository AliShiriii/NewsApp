package com.example.newsapp.repository

import com.example.newsapp.api.NewsApi
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsApi: NewsApi) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) = newsApi.getBreakingNews(countryCode, pageNumber)
    suspend fun searchNews(searchQuery: String, pageNumber: Int) = newsApi.searchForNews(searchQuery, pageNumber)

}
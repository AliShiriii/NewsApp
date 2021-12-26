package com.example.newsapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.engine.Resource
import com.example.newsapp.model.Article
import com.example.newsapp.model.NewsResponse
//import com.example.newsapp.repository.NewsRepository
//import com.example.newsapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

//@HiltViewModel
//class NewsViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {
//
//    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
//    val breakingNewsPage = 1
//
////    init {
////
////        getBreakingNews("us")
////
////    }
//
//    private fun getBreakingNews(countryCode: String) =
//
//        viewModelScope.launch {
//
//            breakingNews.postValue(Resource.Loading())
//            val response = repository.getBreakingNews(countryCode, breakingNewsPage)
//            breakingNews.postValue(handleBreakingNewsResponse(response))
//        }
//
//    private fun handleBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
//
//        if (response.isSuccessful){
//            response.body()?.let {resultResponse ->
//                return Resource.Success(resultResponse)
//            }
//
//        }
//
//        return Resource.Error(response.message())
//    }
//}
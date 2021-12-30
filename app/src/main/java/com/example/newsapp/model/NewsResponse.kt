package com.example.newsapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
): Parcelable
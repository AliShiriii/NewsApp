package com.example.newsapp.di

import android.content.Context
import androidx.room.Room
import com.example.newsapp.api.Connectivity
import com.example.newsapp.api.NewsApi
import com.example.newsapp.api.PrettyPrintLogger
import com.example.newsapp.utils.Constants.Companion.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideConnectivity(@ApplicationContext context: Context): Connectivity = Connectivity(context)

    @Provides
    @Singleton
    fun provideGsonBuilder() : GsonBuilder = GsonBuilder()

    @Provides
    @Singleton
    fun provideGson(builder: GsonBuilder): Gson = builder.setLenient().create()

    @Provides
    @Singleton
    fun provideLogIntercept(builder: GsonBuilder): HttpLoggingInterceptor {
        val logging: HttpLoggingInterceptor = HttpLoggingInterceptor(PrettyPrintLogger(builder))
        //it is a good practice not to write logs in release
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }

    @Singleton
    @Provides
    fun provideOkHttp(logging: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .readTimeout(8000, TimeUnit.SECONDS)
            .writeTimeout(8000, TimeUnit.SECONDS)
            .connectTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit =

        Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): NewsApi =
        retrofit.create(NewsApi::class.java)

    @Provides
    @Singleton
    fun provideFavoriteMoveDataBase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            NewsDataBase::class.java,
            "news_db"
        ).build()

    @Provides
    @Singleton
    fun provideFavoriteMoveDao(db: NewsDataBase) = db.getDao()

}
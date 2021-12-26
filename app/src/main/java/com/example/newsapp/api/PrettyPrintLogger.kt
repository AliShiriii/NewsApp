package com.example.newsapp.api

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import okhttp3.logging.HttpLoggingInterceptor

//class PrettyPrintLogger(builder: GsonBuilder) : HttpLoggingInterceptor.Logger {
//    private val gson: Gson
//    override fun log(message: String) {
//        if (message != null && !message.startsWith("{")) {
//            print(message)
//            return
//        }
//        try {
//            val prettyPrintJson = """
//                ${gson.toJson(JsonParser().parse(message))}
//
//                """.trimIndent()
//            var i = 0
//            val length = prettyPrintJson.length
//            while (i < length) {
//                val min = Math.min(MAX_LOG_LENGTH, prettyPrintJson.length - i)
//                var end = prettyPrintJson.substring(i, i + min).lastIndexOf('\n')
//                end = if (end != -1) end else length
//                Log.d("OkHttp", (if (i > 1) " " else "") + prettyPrintJson.substring(i, i + end))
//                i += end + 1
//                i++
//            }
//        } catch (m: Exception) {
//            print(message)
//        }
//    }
//
//    private fun print(message: String) {
//        val logLevel = Log.DEBUG
//
//        // Split by line, then ensure each line can fit into Log's maximum length.
//        var i = 0
//        val length = message.length
//        while (i < length) {
//            var newline = message.indexOf('\n', i)
//            newline = if (newline != -1) newline else length
//            do {
//                val end = Math.min(newline, i + MAX_LOG_LENGTH)
//                Log.println(logLevel, "OkHttp", message.substring(i, end))
//                i = end
//            } while (i < newline)
//            i++
//        }
//    }
//
//    companion object {
//        private const val MAX_LOG_LENGTH = 4000
//    }
//
//    init {
//        gson = builder.setPrettyPrinting().create()
//
//    }
//}
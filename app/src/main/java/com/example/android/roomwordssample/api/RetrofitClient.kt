package com.example.android.roomwordssample.api

import android.content.Context
import android.os.Handler
import com.example.android.roomwordssample.helpers.UtilsConstant
import com.example.android.roomwordssample.interfaces.CallBack
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

//class RetrofitClient(context: Context) {
//    var BASE_URL= UtilsConstant.BASE_URL
////    companion object{
////        var isAppendV2:Boolean = false
////    }
//
//    var okHttpClient = OkHttpClient.Builder()
//        .connectTimeout(60, TimeUnit.SECONDS)
//        .readTimeout(60, TimeUnit.SECONDS)
//        .writeTimeout(60, TimeUnit.SECONDS)
//        .addInterceptor(addHttpLoggingInterceptor())
//        .addInterceptor{ chain ->
//            val original = chain.request()
//            val requestBuilder = original.newBuilder()
//                .addHeader("Content-Type", "application/*; charset=utf-8")
//                .addHeader("Accept", "*/*")
//                .method(original.method(), original.body())
//
//            val request = requestBuilder.build()
//            chain.proceed(request)
//        }
//        .addInterceptor { chain ->
//            val request = chain.request()
//            val response = chain.proceed(request)
//            response
//        }
//        .build()
//
//
//    val instance : ApiService by lazy {
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(okHttpClient)
//            .build()
//
//        retrofit.create(ApiService::class.java)
//    }
//
//    fun addHttpLoggingInterceptor(): Interceptor {
//        val logRequestResponseInterceptor = HttpLoggingInterceptor()
//        logRequestResponseInterceptor.level = HttpLoggingInterceptor.Level.BODY
//        return logRequestResponseInterceptor
//    }
//}

object RetrofitClient {
    private val BASE_URL = "https://api.github.com/"
    private var retrofit: Retrofit? = null

    val service: ApiService
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit!!.create<ApiService>(ApiService::class.java!!)
        }
}
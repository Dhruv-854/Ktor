package com.example.ktor1

import com.example.ktor1.data.Api
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: Api = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Api.BASE_URL)
        .build()
        .create(Api::class.java)
}
package com.example.ktor1.data

import com.example.ktor1.data.model.Products
import retrofit2.http.GET

interface Api {
//    @GET("products/{type}")
//    suspend fun getProductList(
//        @Path("type") type : String,
//        @Query("api_key") page : Int,
//        @Query("api_key") apiKey :String
//    ): Products


    @GET("products")
    suspend fun getProductList() : Products

    companion object{
        const val BASE_URL = "https://dummyjson.com/"
    }

}
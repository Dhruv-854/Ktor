package com.example.ktor1.data

import com.example.ktor1.data.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProductList(): Flow<Result<List<Product>>>
}
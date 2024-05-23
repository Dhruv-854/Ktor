package com.example.ktor1.data

import com.example.ktor1.data.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class ProductRepositoryImpl (
    private val api: Api
): ProductRepository {
    override suspend fun getProductList(): Flow<Result<List<Product>>> {
        return flow {
            val productsFromApi = try {
                api.getProductList()
            } catch (e:IOException){
                e.printStackTrace()
                emit(Result.Error(message = "Error loading Products"))
                return@flow
            }catch (e : retrofit2.HttpException){
                e.printStackTrace()
                emit(Result.Error(message = "Error loading Products"))
                return@flow
            }catch (e:Exception){
                e.printStackTrace()
                emit(Result.Error(message = "Error loading Products"))
                return@flow
            }
            emit(Result.Success(productsFromApi.products))
        }

    }

}
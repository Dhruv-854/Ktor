package com.example.ktor1.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktor1.data.ProductRepository
import com.example.ktor1.data.model.Product
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class ProductsViewModel(
    private val productRepository: ProductRepository,
) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products = _products.asStateFlow()
    private val _showErrorToastChannel = Channel<Boolean>()
    val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            productRepository.getProductList().collectLatest { result ->
                when (result) {

                    is com.example.ktor1.data.Result.Error -> {
                        _showErrorToastChannel.send(true)
                    }

                    is com.example.ktor1.data.Result.Success -> {
                        result.data?.let { products ->
                            _products.update { products }
                        }
                    }
                }
            }
        }
    }

}
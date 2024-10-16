package com.example.domaci3.model.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import rs.edu.raf.raf_vezbe3compose.passwords.repository.ProductRepository
import java.io.IOException

class ProductListViewModel (
    private val repository: ProductRepository = ProductRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ProductListState())
    val state = _state.asStateFlow()
    private fun setState(reducer: ProductListState.() -> ProductListState) = _state.getAndUpdate(reducer)


    init {
        observeProducts()
        //fetchProducts()
    }

    private fun observeProducts() {
        viewModelScope.launch {
            repository.observeProducts().collect {
                setState { copy(products = it) }
            }
        }
    }

    fun fetchProducts() {
        viewModelScope.launch {
//            setState { copy(fetching = true) }
            try {
                withContext(Dispatchers.IO) {
                    repository.fetchProducts()
                }
            } catch (error: IOException) {
//                setState { copy(error = PasswordListState.ListError.ListUpdateFailed(cause = error)) }
//            } finally {
//                setState { copy(fetching = false) }
            }
        }
    }
}

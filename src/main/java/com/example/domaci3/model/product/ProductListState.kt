package com.example.domaci3.model.product

import com.example.domaci3.domain.ProductData

data class ProductListState(
//    val fetching: Boolean = false,
    val products: List<ProductData> = emptyList(),
//    val error: ListError? = null
) {
//    sealed class ListError {
//        data class ListUpdateFailed(val cause: Throwable? = null) : ListError()
//    }
}

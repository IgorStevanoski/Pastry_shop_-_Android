package rs.edu.raf.raf_vezbe3compose.passwords.repository

import com.example.domaci3.domain.ProductData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

object ProductRepository {

    private val products = MutableStateFlow(listOf<ProductData>())

    fun allProducts(): List<ProductData> = products.value

    suspend fun fetchProducts() {
        products.update { ProductSampleData.toMutableList() }
    }

    fun observeProducts(): Flow<List<ProductData>> = products.asStateFlow()

    fun observeProductDetails(productId: String): Flow<ProductData?> {
        return observeProducts()
            .map { products -> products.find { it.id == productId } }
            .distinctUntilChanged()
    }

    fun getProductById(id: String): ProductData? {
        return products.value.find { it.id == id }
    }

    fun updateOrInsertProduct(id: String, data: ProductData) {
        products.update { list ->
            val index = list.indexOfFirst { it.id == id }
            if (index != -1) {
                list.toMutableList().apply {
                    this[index] = data
                }
            } else {
                list.toMutableList().apply {
                    add(data)
                }
            }
        }
    }
}

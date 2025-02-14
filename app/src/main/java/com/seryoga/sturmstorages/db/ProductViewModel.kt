package com.seryoga.sturmstorages.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn

class ProductViewModel(private val dao: Dao) : ViewModel() {
    private val _product = MutableStateFlow("%")
    private val _provider = MutableStateFlow("%")

    @OptIn(ExperimentalCoroutinesApi::class)
    val getProduct: StateFlow<List<Product>> =
        combine(_product, _provider){ product, provider ->
        product to provider
    }.flatMapLatest { (product, provider) ->
            dao.getSomeProducts( product, provider)
        }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())


    fun productFilter(product: String) {
        var result = product
        if (result.isEmpty()) {
            result = "%"
        } else {
            result = "%${result}%"
        }
        _product.value = result
    }
    fun providerFilter(provider: String) {
        _provider.value = provider
    }

    suspend fun addProduct(products: List<Product>) {
        dao.insertProducts(products)
    }

    val providers : LiveData<List<String>> = dao.getProvider()

}
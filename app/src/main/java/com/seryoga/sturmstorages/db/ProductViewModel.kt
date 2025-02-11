package com.seryoga.sturmstorages.db

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn

class ProductViewModel(private val dao: Dao) : ViewModel() {
    private val _filter = MutableStateFlow("")
    val filter: StateFlow<String> = _filter

    @OptIn(ExperimentalCoroutinesApi::class)
    val products: StateFlow<List<Product>> = _filter
        .flatMapLatest { filter ->
            dao.getNewProducts(filter)
        }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun setFilter(newFilter: String) {
        _filter.value = newFilter
    }

    suspend fun addProduct(products: List<Product>) {
        dao.insertProducts(products)
    }
}
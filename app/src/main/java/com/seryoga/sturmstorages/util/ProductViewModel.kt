package com.seryoga.sturmstorages.util

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.seryoga.sturmstorages.db.Dao
import com.seryoga.sturmstorages.db.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class ProductViewModel(private val dao: Dao) : ViewModel() {
    private val _product = MutableStateFlow("%")
    private val _provider = MutableStateFlow("%")

    @OptIn(ExperimentalCoroutinesApi::class)
    val getProduct: StateFlow<List<Product>> =
        combine(_product, _provider) { product, provider ->
            product to provider
        }.flatMapLatest { (product, provider) ->
            dao.getSomeProducts(product, provider)
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

    val providers: List<String> = runBlocking {
        dao.getProvider()
    }


    /*===================================================*/

//    private var _topHeight = 56.dp
//    fun setTopHeight(topHeight : Dp){
//        _topHeight = topHeight
//    }
//    fun getHeight() {
//
//    }

//    val providers: LiveData<List<String>> = Transformations.map(yourDao.getDistinctProviders()) { it }

    //    first state whether the search is happening or not
//    private val _isSearching = MutableStateFlow(false)
//    val isSearching  = _isSearching.asStateFlow()
//
//    //    second state the text typed by the user
//    private val _searchText = MutableStateFlow("")
//    val searchText = _searchText.asStateFlow()
//
//    //    third state the list to be filtered
//    private val _providersList = MutableStateFlow(providers)
//    val providersList = searchText
//        .combine(_providersList) {text, provider ->
//            if(text.isBlank()) {
//                providers
//            }
//            providers.filter{provider ->
//                provider.uppercase().contains(text.trim().uppercase())
//            }
//        }.stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5000),
//            initialValue = _providersList.value
//        )
//
//    fun onSearchTextChange(text : String) {
//        _searchText.value = text
//    }
//
//    fun onToogleSearch() {
//        _isSearching.value = !_isSearching.value
//        if(!isSearching.value) onSearchTextChange("")
//    }
}


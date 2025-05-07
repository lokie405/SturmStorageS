package com.seryoga.sturmstorages.util

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.seryoga.sturmstorages.db.Dao
import com.seryoga.sturmstorages.db.Product
import com.seryoga.sturmstorages.util.Const.TAG
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ViewModelProduct(private val dao: Dao) : ViewModel() {


    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    var productsInput by mutableStateOf(listOf("%", "%"))
    var providerInput by mutableStateOf("%")
//    fun loadProducts(productQueries: List<String>, provider: String) {
    fun loadProducts() {
        val query = buildQuery(productsInput, providerInput)
        viewModelScope.launch {
            dao.getSomeProductRaw(query)
                .collect { _products.value = it }
        }
    }

    private fun buildQuery(productQueries: List<String>, provider: String): SupportSQLiteQuery {
        val sqlBuilder = StringBuilder("SELECT * FROM ${Const.TABLE_PRODUCT_NAME} WHERE ")
        val args = mutableListOf<Any>()

        productQueries.forEachIndexed { index, q ->
            if (index > 0) sqlBuilder.append(" AND ")
            sqlBuilder.append("product LIKE ?")
            args.add("%$q%")
        }

        sqlBuilder.append(" AND provider LIKE ?")
        args.add(provider)
        Log.i(TAG, "--ViewModelProduct: sqlBuilder = ${sqlBuilder.toString()}")

        val finalSql = sqlBuilder.toString()
        val filledSql = args.foldIndexed(finalSql) { i, acc, arg ->
            acc.replaceFirst("?", "'${arg.toString().replace("'", "''")}'")
        }
        Log.i(TAG, "--ViewModelProduct: filledSql = $filledSql")
        return SimpleSQLiteQuery(sqlBuilder.toString(), args.toTypedArray())
    }



    private val _product = MutableStateFlow("%")
    private val _provider = MutableStateFlow("%")


    @OptIn(ExperimentalCoroutinesApi::class)
    val getProduct: StateFlow<List<Product>> =
        combine(_product, _provider) { product, provider ->
            product to provider
        }.flatMapLatest { (product, provider) ->
            dao.getSomeProducts(product, provider)
        }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())



    /*- Raw-*/
//    @OptIn(ExperimentalCoroutinesApi::class)
//    val getProduct: StateFlow<List<Product>> =
//        dao.getSomeProductRaw()




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


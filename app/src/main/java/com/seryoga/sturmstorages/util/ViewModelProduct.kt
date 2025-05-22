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
import com.seryoga.sturmstorages.db.ProductNew
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.math.log

class ViewModelProduct(private val dao: Dao) : ViewModel() {


    private val _dateUpdate = MutableStateFlow<String>("empty")
    val dateUpdate: StateFlow<String> = _dateUpdate

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products


    private val _productsNew = MutableStateFlow<List<ProductNew>>(emptyList())
    val productsNew: StateFlow<List<ProductNew>> = _productsNew

    suspend fun setProductsNew() {
        _productsNew.value = dao.getProductsNew()
    }
//    private val _productsNew2 = MutableStateFlow<List<ProductNew>>(emptyList())
//    val productsNew2: StateFlow<List<ProductNew>> = _productsNew2


//    private val _productsOld2 = MutableStateFlow<List<ProductOld>>(emptyList())
//    val productsOld2: StateFlow<List<ProductOld>> = _productsOld2

    var productsInput by mutableStateOf(listOf("%", "%"))
    var providerInput by mutableStateOf("%")

    private val _progress = MutableStateFlow(0f)
    val progress: StateFlow<Float> = _progress


    //  --- data update ---
    fun setDataUpdate(dataUpdate: String) {
        _dateUpdate.value = dataUpdate
    }

    //  --- Progress ---
    fun setProgress(value: Float) {
        _progress.value = value
        if (value > 0.99f) {
            Log.i("MyLog", "progress: ${value}")
        }
    }

    fun displayProducts() {
        val query = buildQuery(productsInput, providerInput)
        viewModelScope.launch {
            dao.getSomeProductRaw(query)
                .collect { _products.value = it }
        }
    }

    private fun buildQuery(productQueries: List<String>, provider: String): SupportSQLiteQuery {
        val sqlBuilder = StringBuilder("SELECT * FROM ${Const.TABLE_PRODUCTS_NAME} WHERE ")
        val args = mutableListOf<Any>()

        productQueries.forEachIndexed { index, q ->
            if (index > 0) sqlBuilder.append(" AND ")
            sqlBuilder.append("product LIKE ?")
            args.add("%$q%")
        }

        sqlBuilder.append(" AND provider LIKE ?")
        args.add(provider)
//        Log.i(TAG, "--ViewModelProduct: sqlBuilder = ${sqlBuilder.toString()}")

        val finalSql = sqlBuilder.toString()
        val filledSql = args.foldIndexed(finalSql) { i, acc, arg ->
            acc.replaceFirst("?", "'${arg.toString().replace("'", "''")}'")
        }
//        Log.i(TAG, "--ViewModelProduct: filledSql = $filledSql")
        return SimpleSQLiteQuery(sqlBuilder.toString(), args.toTypedArray())
    }


    suspend fun addProduct(products: List<Product>) {
        dao.insertProducts(products)
    }

    suspend fun addToProductsNew(products: List<ProductNew>) {
        dao.insertProductsNew(products)
    }

    val providers: List<String> = runBlocking {
        dao.getProvider()
    }

//  --- Update ---

    suspend fun copyFromNewToCurrent() {
        val productsNew = dao.getProductsNew()
        val products = dao.getProductsAll()
        Log.i("MyLog", "Start copy");
        if (products.isEmpty()) {
        Log.i("MyLog", "Start copy22222222222222222 ${productsNew.size}");
            dao.insertProducts(productsNew.map {
//            Log.i("MyLog", "(_)_)_)___${it.name}");
                Product(
                    name = it.name,
                    quantity = it.quantity,
                    price = it.price,
                    provider = it.provider
                )
            })
        }

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


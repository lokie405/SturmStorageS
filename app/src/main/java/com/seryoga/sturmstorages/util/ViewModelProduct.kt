package com.seryoga.sturmstorages.util

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.seryoga.sturmstorages.db.Dao
import com.seryoga.sturmstorages.db.Product
import com.seryoga.sturmstorages.db.ProductNew
import io.ktor.client.network.sockets.*
import io.ktor.utils.io.errors.*
import java.net.*
import com.seryoga.sturmstorages.model.LoadState
//import com.seryoga.sturmstorages.model.LoadState.CONNECTING
//import com.seryoga.sturmstorages.model.LoadState.ERROR_NO_DATA
import com.seryoga.sturmstorages.model.ProductState
import com.seryoga.sturmstorages.model.ProductsStatus
import com.seryoga.sturmstorages.model.SettingData
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONException
import org.json.JSONObject

class ViewModelProduct(private val dao: Dao) : ViewModel() {

    var isLoad = false

    //  --- New ---
    private val _dateNew = MutableStateFlow<String>(Const.NULL_DATE_PATTERN)
    val dateNew: StateFlow<String> = _dateNew
    fun setDateNew(value: String) {
        _dateNew.value = value
    }

    private val _sizeNew = MutableStateFlow<Int>(0)
    val sizeNew: StateFlow<Int> = _sizeNew
    fun setSizeNew(value: Int) {
        _sizeNew.value = value
    }

    //  --- Current ---
    private val _dateCurrent = MutableStateFlow<String>(Const.NULL_DATE_PATTERN)
    val dateCurrent: StateFlow<String> = _dateCurrent
    fun loadCurrentDate() {
        try {
            viewModelScope.launch {
                val date = dao.getCurrentDate() ?: Const.NULL_DATE_PATTERN
                _dateCurrent.value = date
//                Log.i("MyLog", "!!!£££!!!${date}");
            }
        } catch (e: Exception) {
            Log.i("MyLog", "Error: cant load current date");
        }
    }

    private val _sizeCurrent = MutableStateFlow<Int>(0)
    val sizeCurrent: StateFlow<Int> = _sizeCurrent
    fun setSizeCurrent(value: Int) {
        _sizeCurrent.value = value
    }

//    fun checkIfProductsExist(): Boolean{
//            var result = false
//        viewModelScope.launch {
//            result = dao.isProductsExist()
//        }
//        return result
//    }

    //  --- Current ---
//    private val _currentDate = MutableLiveData<String?>()
//    val currentDate: LiveData<String?> = _currentDate


    //  --- Old ---
    private val _dateOld = MutableStateFlow<String>(Const.NULL_DATE_PATTERN)
    val dateOld: StateFlow<String> = _dateOld
    fun loadOldDate() {
        try {
            viewModelScope.launch {
                val date = dao.getOldDate() ?: Const.NULL_DATE_PATTERN
                _dateOld.value = date
            }
        } catch (e: Exception) {
            Log.i("MyLog", "Error: cant load old date");
        }
    }

    private val _sizeOld = MutableStateFlow<Int>(0)
    val sizeOld: StateFlow<Int> = _sizeOld
    fun setSizeOld(value: Int) {
        _sizeOld.value = value
    }


    private val _dateUpdate = MutableStateFlow<String>("empty")
    val dateUpdate: StateFlow<String> = _dateUpdate

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products


    private val _productsNew = MutableStateFlow<List<ProductNew>>(emptyList())
    val productsNew: StateFlow<List<ProductNew>> = _productsNew

    suspend fun setProductsNew() {
        _productsNew.value = dao.getProductsNew()
    }

    //  --- New Load ---

    private val _productStatus = MutableStateFlow(ProductsStatus(ProductState.EMPTY))
    val productStatus: StateFlow<ProductsStatus> = _productStatus.asStateFlow()

    fun updateNewProduct(state: ProductState) {
        _productStatus.value = _productStatus.value.copy(newProduct = state)
    }

    fun updateCurrentProduct(state: ProductState) {
        _productStatus.value = _productStatus.value.copy(currentProduct = state)
    }

    fun updateOldProduct(state: ProductState) {
        _productStatus.value = _productStatus.value.copy(oldProduct = state)
    }


    //  --- State ---
    private val _state = MutableStateFlow<LoadState?>(LoadState.CONNECTING)
    val state: StateFlow<LoadState?> = _state
    suspend fun setLoadState(stateNew: LoadState) {
        _state.value = stateNew
        Log.i("MyLog", "_state[]: ${stateNew.label}");
    }

    private val _productsToLoad = mutableStateListOf<ProductNew>()
    val productsToLoad: List<ProductNew> get() = _productsToLoad

    val settingData = mutableStateOf(SettingData())

    @SuppressLint("SuspiciousIndentation")
    fun loadProducts(context: Context) {
        if (!isLoad) {
            isLoad = true  //  To prevent duplicate loading
            viewModelScope.launch {
                setLoadState(LoadState.CONNECTING)
//                _state.value = LoadState.CONNECTING
                launch {
                    var sec = 0
                    while (_state.value == LoadState.CONNECTING) {
                        setProgress(sec.toString())
                        delay(1_000)
                        sec++
                    }
                }
//                Log.i("MyLog", "_state(1): ${state.value?.label}")
                try {
                    val client = HttpClient(CIO) {
                        engine {
                            requestTimeout = 25_000
                        }
                    }
                    val response: HttpResponse = client.get(settingData.value.url)

                        setLoadState(LoadState.CONNECTED)
                    delay(500)
//                    delay(2000)
//                    Log.i("MyLog", "_state(2): ${state.value?.label}")

                    val jsonString = response.bodyAsText()
                    val json = JSONObject(jsonString)
                    if (!json.has("data") || json.getJSONArray("data").length() == 0) {
                        setLoadState(LoadState.ERROR_NO_DATA)
                        return@launch
                    }

                    val jsonArray = json.getJSONArray("data")

//                    _dateNew.value = jsonArray[0].toString()

                    _dateNew.value = jsonArray.getJSONObject(0).getString("date").replace(Regex("\\.\\d{2}$"), "")
                    _sizeNew.value = jsonArray.length()
//                    Log.i("MyLog", "dateNew = ${dateCurrent.value}");
                    setLoadState(LoadState.START_LOADING)
//                        setLoadState(LoadState.LOADING_ITEM)
                    launch {
                        setProgress(jsonArray.length().toString())
                    }
                    delay(1000)
//                    Log.i("MyLog", "_state(3): ${state.value?.label} + jsonArray.size = ${jsonArray.length()}");

                    val newProducts = mutableListOf<ProductNew>()
                    for (i in 1 until jsonArray.length()) {
//            Log.i("MyLog", "_state(3.1): ${state.value?.label} + jsonArray.size = ${jsonArray.get(i)}");
                        val obj = jsonArray.getJSONObject(i)
//            Log.i("MyLog", "_state(4): ${state.value?.label} + name: ${obj.getString("name")}");

                        val product = ProductNew(
                            name = obj.getString("name"),
                            price = obj.getString("price"),
                            quantity = obj.getString("quantity"),
                            provider = obj.getString("provider"),
                            date = dateNew.value,
                        )
                        newProducts.add(product)
//                    setProgress(i.toFloat() / jsonArray.length().toFloat())
                    }

                    _productsToLoad.clear()
                    _productsToLoad.addAll(newProducts)

                    setLoadState(LoadState.FINISHED_LOAD)
//                    Log.i("MyLog", "_state(5): ${state.value?.label}");
                    client.close()
                    runBlocking {
                        setLoadState(LoadState.START_ADD_T0_NEW)
//                        Log.i("MyLog", "_state(6): ${state.value?.label}");
                        addToProductsNew(newProducts)
                        setLoadState(LoadState.FINISH_ADD_T0_NEW)
//                        Log.i("MyLog", "_state(6): ${state.value?.label}");
                        updateNewProduct(ProductState.FULL)
//                        Log.i("MyLog", "_state(7): ${state.value?.label}");
                        setLoadState(LoadState.NEW_DATA_READY)

                    }

                } catch (e: Exception) {
                    @SuppressLint("ServiceCast")

                    _state.value = when (e) {
                        is UnknownHostException,
                        is ConnectTimeoutException,
                        is SocketTimeoutException,
                        is IOException,
                            -> LoadState.ERROR_NO_INTERNET

                        is JSONException -> LoadState.ERROR_NO_DATA
                        else -> LoadState.ERROR
                    }
//                    if(isConnected(context)) _state.value = ERROR_NO_INTERNET
                    viewModelScope.launch {


                    }
//                    Log.i("j", "_state(6): ${state.value?.label} + error $e");
                }
//                isLoad = false
            }
        }
    }


//    private val _productsNew2 = MutableStateFlow<List<ProductNew>>(emptyList())
//    val productsNew2: StateFlow<List<ProductNew>> = _productsNew2


//    private val _productsOld2 = MutableStateFlow<List<ProductOld>>(emptyList())
//    val productsOld2: StateFlow<List<ProductOld>> = _productsOld2

    var productsInput by mutableStateOf(listOf("%", "%"))
    var providerInput by mutableStateOf("%")

    private val _progress = MutableStateFlow("")
    val progress: StateFlow<String> = _progress


    //  --- data update ---
//    fun setDataUpdate(dataUpdate: String) {
//        _dateUpdate.value = dataUpdate
//    }

    //  --- Progress ---
    fun setProgress(value: String) {
        _progress.value = value
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
//        Log.i("MyLog", "Start copy");
        if (products.isEmpty()) {
            Log.i("MyLog", "Start copy22222222222222222 ${productsNew.size}");
            dao.insertProducts(productsNew.map {
//            Log.i("MyLog", "(_)_)_)___${it.name}");
                Product(
                    name = it.name,
                    quantity = it.quantity,
                    price = it.price,
                    provider = it.provider,
                    date = it.date,
                )
            })
        }
//        Log.i("MyLog", "END copy from new to current");

    }

}
package com.seryoga.sturmstorages.util

import SettingStoreManager
import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.runtime.collectAsState
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
import com.seryoga.sturmstorages.model.AutoUpdatesType
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
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONException
import org.json.JSONObject
import kotlin.math.log

class ViewModelProduct(
    private val dao: Dao,
    private val settingStoreManager: SettingStoreManager,
) : ViewModel() {

    var isLoad = false


    //  --- New ---

    suspend fun getNewDate(): String {
        return dao.getNewDate() ?: Const.NULL_DATE_PATTERN
    }

    private val _dateNew = MutableStateFlow<String>(Const.NULL_DATE_PATTERN)
    val dateNew: StateFlow<String> = _dateNew
    fun setDateNew(value: String) {
        _dateNew.value = value
    }


//    // ---date new test
//    var dateNewS by mutableStateOf(Date())
//        private set
//
//    fun updateNewDateS(value: String) {
//        val format = SimpleDateFormat("dd.MM.yy HH:mm:ss", Locale.getDefault())
//        dateNewS = format.parse(value) ?: format.parse("00.00.00 00:00:00")!!
//    }


    private val _sizeNew = MutableStateFlow<Int>(0)
    val sizeNew: StateFlow<Int> = _sizeNew
    fun setSizeNew(value: Int) {
        _sizeNew.value = value
    }

    fun deleteAllNew() {
        viewModelScope.launch {
            dao.deleteAllNew()
        }
    }


    suspend fun addToProductsNew(products: List<ProductNew>) {
        dao.insertProductsNew(products)
    }

    //  --- Current ---

//    suspend fun getCurrentDate(){
//       return dao.getCurrentDate() ?: Const.NULL_DATE_PATTERN
//    }

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    private val _dateCurrent = MutableStateFlow<String>(Const.NULL_DATE_PATTERN)
    val dateCurrent: StateFlow<String> = _dateCurrent
    suspend fun loadCurrentDate() {
        try {
//            viewModelScope.launch {
            val date = dao.getCurrentDate() ?: Const.NULL_DATE_PATTERN
            _dateCurrent.value = date
//                Log.i("MyLog", "!!!£££!!!${date}");
//            }
        } catch (e: Exception) {
//            Log.i("MyLog", "Error: cant load current date");
        }
    }

//    private val _sizeCurrent = MutableStateFlow<Int>(0)
//    val sizeCurrent: StateFlow<Int> = _sizeCurrent
//    fun setSizeCurrent(value: Int) {
//        _sizeCurrent.value = value
//    }

    fun deleteAllCurrent() {
        viewModelScope.launch {
            dao.deleteAllCurrent()
        }
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

    suspend fun getOldDate() {
        dao.getOldDate() ?: Const.NULL_DATE_PATTERN
    }
//    private val _dateOld = MutableStateFlow<String>(Const.NULL_DATE_PATTERN)
//    val dateOld: StateFlow<String> = _dateOld
//    fun loadOldDate() {
//        try {
//            viewModelScope.launch {
//                val date = dao.getOldDate() ?: Const.NULL_DATE_PATTERN
//                _dateOld.value = date
//            }
//        } catch (e: Exception) {
//            Log.i("MyLog", "Error: cant load old date");
//        }
//    }

//    private val _sizeOld = MutableStateFlow<Int>(0)
//    val sizeOld: StateFlow<Int> = _sizeOld
//    fun setSizeOld(value: Int) {
//        _sizeOld.value = value
//    }

    suspend fun deleteAllOld() {
        dao.deleteAllOld()
    }


//    private val _dateUpdate = MutableStateFlow<String>("empty")
//    val dateUpdate: StateFlow<String> = _dateUpdate


//    private val _productsNew = MutableStateFlow<List<ProductNew>>(emptyList())
//    val productsNew: StateFlow<List<ProductNew>> = _productsNew

//    suspend fun setProductsNew() {
//        _productsNew.value = dao.getProductsNew()
//    }

    //  --- New Load ---

//    private val _productStatus = MutableStateFlow(ProductsStatus(ProductState.EMPTY))
//    val productStatus: StateFlow<ProductsStatus> = _productStatus.asStateFlow()

//    fun updateNewProductStatus(state: ProductState) {
//        _productStatus.value = _productStatus.value.copy(newProduct = state)
//    }

//    fun updateCurrentProductStatus(state: ProductState) {
//        _productStatus.value = _productStatus.value.copy(currentProduct = state)
//    }

//    fun updateOldProductStatus(state: ProductState) {
//        _productStatus.value = _productStatus.value.copy(oldProduct = state)
//    }


    //  --- State ---
    private val _state = MutableStateFlow<LoadState?>(LoadState.CONNECTING)
    val state: StateFlow<LoadState?> = _state
    suspend fun setLoadState(stateNew: LoadState) {
        _state.value = stateNew
//        Log.i("MyLog", "_state[]: ${stateNew.label}");
    }

//    private val _productsToLoad = mutableStateListOf<ProductNew>()
//    val productsToLoad: List<ProductNew> get() = _productsToLoad

    val settingData = mutableStateOf(SettingData())


    //  --- Load from Google Sheet ---
    @SuppressLint("SuspiciousIndentation")
    fun loadProducts(context: Context) {
//        Log.i("MyLog", "START LOAD PRODUCT");
        if (!isLoad) {
            isLoad = true  //  To prevent duplicate loading
//        Log.i("MyLog", "IS LOAD = TRUE");
            viewModelScope.launch {
                setLoadState(LoadState.CONNECTING)
//                _state.value = LoadState.CONNECTING
                launch {
                    var sec = 0
                    while (state.value == LoadState.CONNECTING) {
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

                    val dateBeforeUpdate = getNewDate()
                    val dateAfterUpdate = jsonArray.getJSONObject(0).getString("date")
//                    Log.i(
//                        "MyLog",
//                        "----____ Date before: ${dateBeforeUpdate}; Date after: ${dateAfterUpdate}; curr: ${dateCurrent.value}"
//                    );
                    setDateNew(dateAfterUpdate)
//                    setLoadState(LoadState.NEED_TO_BE_UPDATE)
                    if (dateBeforeUpdate == dateAfterUpdate) {
//                        Log.i("MyLog", "same date");
                        setLoadState(LoadState.NO_NEED_TO_UPDATE)
                    } else {

                        val autoupdateType = settingStoreManager.getAutoUpdateType().first()

//                    Log.i("MyLog", "settingStoreManager.getAutoUpdateType(): ${settingStoreManager.getAutoUpdateType().first()}");
//                    updateNewDateS(jsonArray.getJSONObject(0).getString("date"))
//                    Log.i("MyLog", "++++++${jsonArray.getJSONObject(0).getString("date")}");
//                    setSizeNew(jsonArray.length())
//                    if (dateNew.value.equals(dateCurrent.value) && sizeNew.value == sizeCurrent.value) {
////                        todo: -------------------------
//                    } else {
//
//                    }
//                    Log.i("MyLog", "dateNew = ${dateCurrent.value}");
                        setLoadState(LoadState.START_LOADING)
                        Log.i(
                            "MyLog",
                            "{{{{{}}}} dateBefore ${dateBeforeUpdate}; dateAfter ${dateAfterUpdate}"
                        );
//                        setLoadState(LoadState.LOADING_ITEM)
//                    launch {
//                        setProgress(jsonArray.length().toString())
//                    }
                        delay(500)
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

//                    _productsToLoad.clear()
//                    _productsToLoad.addAll(newProducts)

                        setLoadState(LoadState.FINISHED_LOAD)
                        client.close()
                        runBlocking {
                            deleteAllNew()
                            addToProductsNew(newProducts)
                            deleteAllCurrent()
                            copyFromNewToCurrent()
                            loadCurrentDate()
                            setLoadState(LoadState.NO_NEED_TO_UPDATE)
                        }
                    }

                } catch (e: Exception) {
                        Log.i("MyLog", "Error connect: $e");
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
//                    viewModelScope.launch {
//
//
//                    }
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


    val providers: List<String> = runBlocking {
        dao.getProvider()
    }

    //  --- Update ---
    suspend fun copyFromNewToCurrent() {
        val productsNew = dao.getProductsNew()
        val products = dao.getProductsAll()
        if (products.isEmpty()) {
//            Log.i("MyLog", "Start copy22222222222222222 prodnew size ${productsNew.size}");
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
    }

    //  --- Make Backup ---
//    Todo: ________________

    suspend fun copyFromCurrentToOld() {
        val productsCurrent = dao.getProductsAll()
//        val products = dao.getProductsAll()
//        if (products.isEmpty()) {
//        Log.i("MyLog", "Start copy22222222222222222 ${productsCurrent.size}");
        dao.insertProducts(
            productsCurrent.map {
//            Log.i("MyLog", "(_)_)_)___${it.name}");
                Product(
                    name = it.name,
                    quantity = it.quantity,
                    price = it.price,
                    provider = it.provider,
                    date = it.date,
                )
//            })
            }
        )
    }

}
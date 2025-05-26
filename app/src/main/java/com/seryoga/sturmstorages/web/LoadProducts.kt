package com.seryoga.sturmstorages.web

import SettingStoreManager
import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.seryoga.sturmstorages.db.ProductNew
import com.seryoga.sturmstorages.model.LoadState
import com.seryoga.sturmstorages.util.ViewModelProduct
import com.seryoga.sturmstorages.util.Const
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@SuppressLint("SuspiciousIndentation")
suspend fun LoadProducts(
    context: Context,
    vmProduct: ViewModelProduct,
    onStatusUpdate: (LoadState, String?) -> Unit

) = coroutineScope {
    val url = SettingStoreManager(context).getURL().first()

    suspendCoroutine<Unit>{continuation ->
            Log.i("MyLog", "[Load]... Start load");
            onStatusUpdate(LoadState.CONNECTING, "CONNECTING")
    var products = mutableListOf<ProductNew>()
    val queue = Volley.newRequestQueue(context)
    val stringRequest = StringRequest(
        Request.Method.GET,
        url,
        { response ->
            onStatusUpdate(LoadState.CONNECTED, "CONNECTED")
            val arrayResp = JSONObject(response).getJSONArray("data")
            onStatusUpdate(LoadState.START_LOADING, "START LOADING")
//            Log.i("MyLog", "[Load]: Before ${vmProduct.dateUpdate.value}");
//            Log.i(TAG, "--LoadProducts: First element = ${arrayResp.get(0)}")
                vmProduct.setDataUpdate(arrayResp.get(0).toString())
//            Log.i("MyLog", "2: After ${vmProduct.dateUpdate.value}");
            for (i in 0 until arrayResp.length()) {
                val obj = arrayResp.getJSONObject(i)
                try {
                    val name = obj.getString("name")
                    val price = obj.getString("price")
                    val quantity = obj.getString("quantity")
                    val provider = obj.getString("provider")

                    val product = ProductNew(
                        name = name,
                        price = price,
                        quantity = quantity,
                        provider = provider,
                        date = ""

                    )
//
//                        vmProduct.setProgress((i.toFloat() / arrayResp.length()))
                    products.add(product)
                    onStatusUpdate(LoadState.LOADING_ITEM, name)
//                viewModel.setProgress(i)

                } catch (exception: Exception) {
                    Log.i("error", "--MainScreen:  ${exception}")
                }
//viewModel.setProgress(i / arrayResp.length().toFloat())
            }
            Log.i("MyLog", "[Load]... End load");
            onStatusUpdate(LoadState.FINISHED_LOAD, "FINISHED")
            runBlocking {
            Log.i("MyLog", "[Load]... Start add to products new");
//                Log.i(TAG, "--LoadProducts: Size of products = ${products.size}")
                vmProduct.addToProductsNew(products)
                continuation.resume(Unit)
            Log.i("MyLog", "[Load]... Finish add to products new");
            }
        }, {
            Log.e(Const.TAG, "SER--$it: ")
        }
    )
    stringRequest.retryPolicy = DefaultRetryPolicy(
        10000,
        DefaultRetryPolicy.DEFAULT_MAX_RETRIES, // Number of retries
        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT // Backoff multiplier
    )
    queue.add(stringRequest)
}
}

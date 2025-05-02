package com.seryoga.sturmstorages.web

import SettingStoreManager
import android.content.Context
import android.util.Log
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.seryoga.sturmstorages.db.Product
import com.seryoga.sturmstorages.model.SettingData
import com.seryoga.sturmstorages.util.ViewModelProduct
import com.seryoga.sturmstorages.util.Const
import com.seryoga.sturmstorages.util.Const.TAG
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.json.JSONObject
import settingStore

suspend fun LoadProducts(
    context: Context, viewModel: ViewModelProduct) {
    var products = mutableListOf<Product>()
    val queue = Volley.newRequestQueue(context)
    val url = SettingStoreManager(context).getURL().first()
    val stringRequest = StringRequest(
        Request.Method.GET,
        url,
        { response ->
            val arrayResp = JSONObject(response).getJSONArray("data")
            Log.i(TAG, "--LoadProducts: Size of arrayResp = ${arrayResp.length()}")
            for (i in 0 until arrayResp.length()) {
                val obj = arrayResp.getJSONObject(i)
                try {
                    val name = obj.getString("name")
                    val price = obj.getString("price")
                    val quantity = obj.getString("quantity")
                    val provider = obj.getString("provider")

                    val product = Product(
                        name = name,
                        price = price,
                        quantity = quantity,
                        provider = provider
                    )

                    products.add(product)

                } catch (exception: Exception) {
                    Log.i("error", "--MainScreen:  ${exception}")
                }
            }
            runBlocking {
//                Log.i(TAG, "--LoadProducts: Size of products = ${products.size}")
                viewModel.addProduct(products)
            }
        }, {
//            Log.i(Const.TAG, "SER--$it: ")
        }
    )
    stringRequest.retryPolicy = DefaultRetryPolicy(
        10000,
        DefaultRetryPolicy.DEFAULT_MAX_RETRIES, // Number of retries
        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT // Backoff multiplier
    )
    queue.add(stringRequest)
}
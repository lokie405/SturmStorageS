package com.seryoga.sturmstorages.screen

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.seryoga.sturmstorages.model.LoadState
import com.seryoga.sturmstorages.model.ProductState
import com.seryoga.sturmstorages.model.ProductsStatus
import com.seryoga.sturmstorages.util.ViewModelProduct
import com.seryoga.sturmstorages.util.Const
import com.seryoga.sturmstorages.util.ViewModelSturm
import kotlinx.coroutines.runBlocking

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MainScreen(
    navController: NavHostController,
    vmProduct: ViewModelProduct = viewModel(),
    vmSturm: ViewModelSturm = viewModel(),
) {

    var searchedProviderText by remember { mutableStateOf("") }
    var listOfProviders by remember { mutableStateOf(vmProduct.providers) }
    val allProvider = vmProduct.providers
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val progress by vmProduct.progress.collectAsState()
//    val focusManager = LocalFocusManager.current

//    var status by remember { mutableStateOf(ProductsStatus.EMPTY_ALL) }?
    val state by vmProduct.state.collectAsState()
    val status by vmProduct.productStatus.collectAsState()


    //    Log.i("MyLog", "1....Content start")
//    LaunchedEffect(Unit) {
    fun isConnected(context: Context): Boolean {  //  For check internet connecting d
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    runBlocking {


        if (!isConnected(context)) vmProduct.setLoadState(LoadState.ERROR_NO_INTERNET)
        else {

            vmProduct.setProductsNew()

            if (vmProduct.dateNew.value == Const.NULL_DATE_PATTERN) {
//                vmProduct.setLoadState(LoadState.FIRST_LAUNCH)
//            Log.i("MyLog", "1.1...Content: ProductsNew empty -> start LoadProducts")
//            isProductNewLoad = true
                vmProduct.loadProducts(context)
            }
        }
//        Log.i("MyLog", "___ProductsNew.value.size = ${vmProduct.productsNew.value.size}");
    }

    if (status.newProduct == ProductState.FULL) {
//        Log.i("MyLog", "NEW PRODUCTS -- FULL");
        runBlocking {

            if (vmProduct.products.value.size == 0) {
//                Log.i("MyLog", "1.2...Content: Products empty -> product.size = 0")
//                Log.i("MyLog", "1.2.1.Content: start vmProduct.copyFromNewToCurrent")
                vmProduct.copyFromNewToCurrent()
                vmProduct.loadCurrentDate()
//                Log.i("MyLog", "___Products.size: ${vmProduct.products.value.size}")
            }
        }
    }
//    }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .systemBarsPadding()
            .imePadding()
            .navigationBarsPadding()
    ) {
        Box(
            modifier = Modifier
                .then(
                    if (vmSturm.isShowProviderList) Modifier.weight(1f)
                    else Modifier.height(vmSturm.topHeight)
                )
        ) {
            TopBar(
                navController,
                vmProduct,
                vmSturm,
//                state!!
            ) {

                ProviderList(
                    vmSturm,
                    vmProduct,
                    listOfProviders,
                    searchedProviderCallback = { textFieldInput ->
                        searchedProviderText = textFieldInput
                        listOfProviders =
                            allProvider.filter { it.contains(textFieldInput, ignoreCase = true) }
                    })
            }
        }


//    coroutineScope.launch {

//        Log.i("MyLog", "AFF ${progress}");

//    }

//        runBlocking {
//                delay(timeMillis = 10000)
//            for (i in 0..10) {
//                delay(timeMillis = 300)
//                vmProduct.setProgress(i / 10f)
//            }
//        }

        Box(
            modifier = Modifier.then(
                if (vmSturm.isShowProviderList) Modifier.height(0.dp)
                else Modifier.weight(vmSturm.contentWeight)
            )
        ) {
            Content(vmProduct)
        }
        Box(
            modifier = Modifier.height(Const.BOTTOM_BAR_HEIGHT)
        ) {
            BottomBar(vmProduct, vmSturm, clearAllCallback = {
                searchedProviderText = ""
                listOfProviders = vmProduct.providers
            })
        }
    }
}

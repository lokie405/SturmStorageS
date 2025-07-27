package com.seryoga.sturmstorages.screen

import SettingStoreManager
import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.seryoga.sturmstorages.model.LoadState
import com.seryoga.sturmstorages.model.SettingData
import com.seryoga.sturmstorages.ui.theme.ColorGreen
import com.seryoga.sturmstorages.util.ViewModelProduct
import com.seryoga.sturmstorages.util.Const
import com.seryoga.sturmstorages.util.ViewModelSturm
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MainScreen(
    navController: NavHostController,
    vmProduct: ViewModelProduct = viewModel(),
    vmSturm: ViewModelSturm = viewModel(),
    settingStoreManager: SettingStoreManager = SettingStoreManager(LocalContext.current),
) {
    val settings by settingStoreManager.settingsFlow.collectAsState(SettingData())
    Log.i("MyLog", "___MainScreen -> ${settings.colorOfProduct}");
    var searchedProviderText by remember { mutableStateOf("") }
    var listOfProviders by remember { mutableStateOf(vmProduct.providers) }
    val allProvider = vmProduct.providers
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val progress by vmProduct.progress.collectAsState()

//val scope = rememberCoroutineScope()

    val state by vmProduct.state.collectAsState()

    runBlocking {
        val parser = SimpleDateFormat("dd.MM.yy HH:mm:ss", Locale.getDefault())
        val currentDate = parser.parse(vmProduct.dateCurrent.value)
        val dayFormatter = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
        val todayDate = Date()
        val currentDay = dayFormatter.format(currentDate!!)
        val todayDay = dayFormatter.format(todayDate)

        Log.i("MyLog", "============RUN BLOCKING==============");
        if (vmProduct.dateCurrent.value == Const.NULL_DATE_PATTERN) {
            vmProduct.loadProducts(context)
        } else {
            /**
             * Better to check is today already updated
             * to prevent excessive server connect
             * */
            if (!settings.isAutoupdate) {
                vmProduct.setLoadState(LoadState.DENY_AUTOUPDATE)
            } else {
                if (currentDay == todayDay) {
                    vmProduct.setLoadState(LoadState.ALREADY_UPDATED_TODAY)
                } else {
                    vmProduct.setLoadState(LoadState.NOT_UPDATED_YET_TODAY)
                    if (!isConnected(context)) vmProduct.setLoadState(LoadState.ERROR_NO_INTERNET)
                    else {
                        vmProduct.loadProducts(context)
                    }
                }

            }
        }
    }
    Column(
        Modifier.imePadding()
    ) {

        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background
                    ),
                    title = {
                        TopBar(
                            navController,
                            vmProduct,
                            vmSturm,
                        ) {
                            if (settings.providerDisplayType) {
                                ProviderList(
                                    vmSturm,
                                    vmProduct,
                                    listOfProviders,
                                    searchedProviderCallback = { textFieldInput ->
                                        searchedProviderText = textFieldInput
                                        listOfProviders =
                                            allProvider.filter { it.contains(textFieldInput, ignoreCase = true) }
                                    })
                            } else {
                                ProviderTile(
                                    vmSturm,
                                    vmProduct, listOfProviders,
                                    searchedProviderCallback = { textFieldInput ->
                                        searchedProviderText = textFieldInput
                                        listOfProviders =
                                            allProvider.filter { it.contains(textFieldInput, ignoreCase = true) }
                                    }
                                )
                            }
                        }
                    },
                )
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = MaterialTheme.colorScheme.background
                ) {
                    BottomBar(vmProduct, vmSturm, clearAllCallback = {
                        searchedProviderText = ""
                        listOfProviders = vmProduct.providers
                    })

//                    }
                }
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                Content(vmProduct)
            }
        }

    }

    //  ___ OLD ___
//    Column(
//        modifier = Modifier
//            .background(MaterialTheme.colorScheme.primary)
//            .systemBarsPadding()
//            .imePadding()
//            .navigationBarsPadding()
//    ) {
//        Box(
//            modifier = Modifier
//                .then(
//                    if (vmSturm.isShowProviderList) Modifier.weight(1f)
//                    else Modifier.height(vmSturm.topHeight)
//                ),
//
//        ) {
//            TopBar(
//                navController,
//                vmProduct,
//                vmSturm,
//            ) {
//                if (settings.providerDisplayType) {
//                    ProviderList(
//                        vmSturm,
//                        vmProduct,
//                        listOfProviders,
//                        searchedProviderCallback = { textFieldInput ->
//                            searchedProviderText = textFieldInput
//                            listOfProviders =
//                                allProvider.filter { it.contains(textFieldInput, ignoreCase = true) }
//                        })
//                } else {
//                    ProviderTile(
//                        vmSturm,
//                        vmProduct,listOfProviders,
//                        searchedProviderCallback = { textFieldInput ->
//                            searchedProviderText = textFieldInput
//                            listOfProviders =
//                                allProvider.filter { it.contains(textFieldInput, ignoreCase = true) }
//                        }
//                    )
//                }
//            }
//        }
//
//        Box(
//            modifier = Modifier.then(
//                if (vmSturm.isShowProviderList) Modifier.height(0.dp)
//                else Modifier.weight(vmSturm.contentWeight)
//            )
//        ) {
//            Content(vmProduct)
//        }
//        Box(
//            modifier = Modifier.height(Const.BOTTOM_BAR_HEIGHT)
//        ) {
//            BottomBar(vmProduct, vmSturm, clearAllCallback = {
//                searchedProviderText = ""
//                listOfProviders = vmProduct.providers
//            })
//        }
//    }

}


fun isConnected(context: Context): Boolean {  //  For check internet connecting d
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
    return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
}
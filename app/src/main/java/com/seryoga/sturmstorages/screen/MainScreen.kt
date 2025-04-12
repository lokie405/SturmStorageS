package com.seryoga.sturmstorages.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.seryoga.sturmstorages.util.ViewModelProduct
import com.seryoga.sturmstorages.ui.theme.MainColor
import com.seryoga.sturmstorages.util.Const
import com.seryoga.sturmstorages.util.Const.TAG
import com.seryoga.sturmstorages.util.ViewModelSturm

@Composable
fun MainScreen(navController: NavHostController, vmProduct: ViewModelProduct = viewModel(), vmSturm: ViewModelSturm = viewModel()) {

    var searchedProviderText by remember { mutableStateOf("") }
    var listOfProviders by remember { mutableStateOf(vmProduct.providers) }
    val allProvider = vmProduct.providers

    val focusManager = LocalFocusManager.current


    Column(
        modifier = Modifier
            .background(MainColor)
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

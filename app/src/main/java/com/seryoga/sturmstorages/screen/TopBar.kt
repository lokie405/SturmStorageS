@file:Suppress("UNREACHABLE_CODE")

package com.seryoga.sturmstorages.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.seryoga.sturmstorages.R
import com.seryoga.sturmstorages.model.LoadState
import com.seryoga.sturmstorages.model.NavRoutes
import com.seryoga.sturmstorages.ui.theme.ColorGreen
import com.seryoga.sturmstorages.ui.theme.ColorRed
import com.seryoga.sturmstorages.ui.theme.ColorYellow
import com.seryoga.sturmstorages.util.ViewModelProduct
import com.seryoga.sturmstorages.util.ViewModelSturm

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavHostController,
    vmProduct: ViewModelProduct,
    vmSturm: ViewModelSturm,
//    status: LoadState,
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current
//    Log.i(TAG, "--TopBar: START")
    var expanded by remember { mutableStateOf(false) }
    val progress = vmProduct.progress.collectAsState()
    val dateNew = vmProduct.dateCurrent.collectAsState()
    val dateCurrent by vmProduct.currentDate.observeAsState()
//    var chosenProvider by remember { mutableStateOf("") }
//    val listOfProviders by viewModel.providers.observeAsState(initial = emptyList())
    val state = vmProduct.state.collectAsStateWithLifecycle()

//    val searchProvider by viewModel.searchText.collectAsState()
//    val isSearching by viewModel.isSearching.collectAsState()
//    val providerList by viewModel.providersList.collectAsState()

//    LaunchedEffect(state) {
//
//    Log.i("MyLog", "++++++++${state.value!!.label}");
//    }

    Row(
        modifier = Modifier
            .fillMaxHeight(),
        verticalAlignment = vmSturm.topVerticalAlignment
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth(0.2f)
                .padding(vertical = 10.dp)
                .height(vmSturm.topElementHeight),

            contentAlignment = Alignment.Center
        ) {
            when (state.value) {
                LoadState.CONNECTING -> {
                    LoadStateDisplayIconText(
                        icon = R.drawable.cloud_connect_icon,
                        tint = ColorYellow,
                        text = "${progress.value} s",
                        textMain = dateCurrent.toString()
                    )
                }

                LoadState.CONNECTED -> {
                    LoadStateDisplayIconText(
                        R.drawable.cloud_checked_icon,
                        ColorGreen,
                        "Connected"
                    )
                }

                LoadState.NEW_DATA_READY -> {
                    LoadStateDisplayTextText(
                        dateNew.value,
                        ColorGreen,
                        progress.value
                    )
                }

                LoadState.ERROR_NO_INTERNET -> {
                    LoadStateDisplayIconText(
                        R.drawable.wifi_slash_icon,
                        ColorRed,
                        "No connect"
                    )
                }

                LoadState.ERROR -> {
                    LoadStateDisplayIconText(
                        R.drawable.error_cross_icon,
                        ColorRed,
                        "Error"
                    )
                }

                LoadState.ERROR_NO_DATA -> {
                    LoadStateDisplayIconText(
                        R.drawable.cloud_xmark_icon,
                        ColorRed,
                        "No data"
                    )
                }

                else -> {
                    LoadStateDisplayIconText(
                        R.drawable.cloud_download_icon,
                        ColorGreen,
                        progress.value
                    )
                }
            }
        }
        content()
        Box(
            modifier = Modifier
                .height(vmSturm.topElementHeight)
                .fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd,
        ) {
            IconButton(
                onClick = {
                    navController.navigate(NavRoutes.Setting.route)
//                    runBlocking {
//
//                        LoadProducts(context, vmProduct)
//                    }

//                    vmSturm.showingScreen = flowOf(Const.SETTING_SCREEN)
//                    vmSturm.setScreen(Screen.SETTING_SCREEN)
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.options_icon),
                    contentDescription = stringResource(R.string.option_button),
                    tint = Color.White
                )
            }


//            IconButton(
//                onClick = { expanded = true },
//            ) {
//                if (chosenProvider.isEmpty()) {
//
//                    Icon(
//                        painter = painterResource(R.drawable.clear_icon),
//                        tint = Color.White,
//                        contentDescription = "All providers"
//                    )
//                } else {
//                    Icon(
//                        painter = painterResource(R.drawable.clear_icon),
//                        tint = Color.White,
//                        contentDescription = "All providers"
//                    )
//
//                }
//            }
        }
//        ProviderSelector(vmSturm, viewModel)


    }

}

@Composable
fun LoadStateDisplayIconText(
    icon: Int,
    tint: Color,
    text: String,
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    textMain: String = "",
    textColorMain: Color = MaterialTheme.colorScheme.onPrimary,
) {
    Column(
        modifier = Modifier
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
            .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Icon(
                modifier = Modifier
                    .height(20.dp),
                painter = painterResource(icon),
                contentDescription = "",
                tint = tint
            )
            Text(
                text = text,
                color = textColor,
            )
        }
        Text(
            text = textMain,
            color = textColorMain,
        )
    }
}

@Composable
fun LoadStateDisplayTextText(
    topText: String,
    topTextColor: Color = MaterialTheme.colorScheme.onPrimary,
    bottomText: String,
    bottomTextColor: Color = MaterialTheme.colorScheme.onPrimary,
) {
    Column(
        modifier = Modifier
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = topText,
            color = topTextColor,
        )
        Text(
            text = bottomText,
            color = bottomTextColor,
        )
    }
}
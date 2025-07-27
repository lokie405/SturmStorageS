//@file:Suppress("UNREACHABLE_CODE")

package com.seryoga.sturmstorages.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.seryoga.sturmstorages.R
import com.seryoga.sturmstorages.model.LoadState
import com.seryoga.sturmstorages.model.NavRoutes
import com.seryoga.sturmstorages.model.TypeOfElement
import com.seryoga.sturmstorages.ui.theme.ColorGreen
import com.seryoga.sturmstorages.ui.theme.ColorRed
import com.seryoga.sturmstorages.ui.theme.ColorYellow
import com.seryoga.sturmstorages.util.ViewModelProduct
import com.seryoga.sturmstorages.util.ViewModelSturm

@Composable
fun TopBar(
    navController: NavHostController,
    vmProduct: ViewModelProduct,
    vmSturm: ViewModelSturm,
    content: @Composable () -> Unit,
) {
//    val context = LocalContext.current
    val progress = vmProduct.progress.collectAsState()
    val dateCurrent by vmProduct.dateCurrent.collectAsState()
    val state = vmProduct.state.collectAsStateWithLifecycle()
//val scope = rememberCoroutineScope()

    Row(
//        modifier = Modifier,
////            .fillMaxHeight(),
//        if(vmSturm.isShowProviderList){
//        verticalAlignment = Alignment.CenterVertically
//        }
//        else verticalAlignment = Alignment.Top
//

    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth(0.2f)
                .padding(vertical = 10.dp)
                .height(vmSturm.topElementHeight),
            contentAlignment = Alignment.Center
        ) {
            when (state.value) {
                LoadState.DENY_AUTOUPDATE -> {
                    LoadStateDisplayIconText(
                        icon = R.drawable.cloud_error_icon,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        typeOfElement = TypeOfElement.ONE_ELEMENTS,
                        textMain = dateCurrent.substring(0, 5)
                    )

                }
                LoadState.CONNECTING -> {
                    LoadStateDisplayIconText(
                        icon = R.drawable.cloud_connect_icon,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        text = "${progress.value} s",
                        textMain = dateCurrent.substring(0, 5)
                    )
                }
                LoadState.CONNECTED -> {
                    LoadStateDisplayIconText(
                        R.drawable.cloud_connect_icon,
                        ColorGreen,
                        "OK",
                        textMain = dateCurrent.substring(0, 5)
                    )

                }

                LoadState.NO_NEED_TO_UPDATE -> {
                    LoadStateDisplayIconText(
                        R.drawable.cloud_checked_icon,
                        ColorGreen,
                        typeOfElement = TypeOfElement.ONE_ELEMENTS,
                        textMain = dateCurrent.substring(0, 5),
                    )
                }
                LoadState.ALREADY_UPDATED_TODAY -> {
                    LoadStateDisplayIconText(
                        R.drawable.calendar_check_icon,
                        ColorGreen,
                        typeOfElement = TypeOfElement.ONE_ELEMENTS,
                        textMain = dateCurrent.substring(0, 5),
                    )
                }
                LoadState.NOT_UPDATED_YET_TODAY -> {
                    LoadStateDisplayIconText(
                        R.drawable.calendar_exclamation_icon,
                        ColorYellow,
                        typeOfElement = TypeOfElement.ONE_ELEMENTS,
                        textMain = dateCurrent.substring(0, 5),
                    )
                }
                LoadState.ERROR_NO_INTERNET -> {
                    LoadStateDisplayIconText(
                        R.drawable.wifi_slash_icon,
                        ColorRed,
                        typeOfElement = TypeOfElement.ONE_ELEMENTS
                    )
                }
                LoadState.ERROR -> {
                    LoadStateDisplayIconText(
                        R.drawable.error_cross_icon,
                        ColorRed,
                        typeOfElement = TypeOfElement.ONE_ELEMENTS
                    )
                }
                LoadState.ERROR_NO_DATA -> {
                    LoadStateDisplayIconText(
                        R.drawable.data_error_icon,
                        ColorRed,
                        typeOfElement = TypeOfElement.ONE_ELEMENTS
                    )
                }
                else -> {
                    LoadStateDisplayIconText(
                        R.drawable.cloud_download_icon,
                        ColorGreen,
                        "Load"
                    )
                }
            }
        }
        content()
        Box(
            modifier = Modifier
                .height(vmSturm.topHeight)
                .fillMaxWidth(),
            contentAlignment = Alignment.CenterStart,
        ) {
            IconButton(
                onClick = {
                    navController.navigate(NavRoutes.Setting.route)
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.setting_icon),
                    contentDescription = stringResource(R.string.option_button),
                    tint = MaterialTheme.colorScheme.onPrimary
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

//@Composable
//fun LoadStateDisplayAnimationText(
//    content: () -> Unit,
//    text: String,
//    textColor: Color = MaterialTheme.colorScheme.onPrimary,
//    textMain: String = "",
//    textColorMain: Color = MaterialTheme.colorScheme.onPrimary,
//) {
//    Column(
//        modifier = Modifier
//            .fillMaxHeight(),
//        verticalArrangement = Arrangement.SpaceBetween,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text = textMain,
//            color = textColorMain,
//        )
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 10.dp),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            content()
//            Text(
//                text = text,
//                color = textColor,
//            )
//        }
//    }
//}

@Composable
fun LoadStateDisplayIconText(
    icon: Int,
    tint: Color,
    text: String = "",
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    textSize: Int = 14,
    textMain: String = "",
    textColorMain: Color = ColorGreen,
    typeOfElement: TypeOfElement = TypeOfElement.TWO_ELEMENTS,
    content: (@Composable () -> Unit)? = null,
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier,
            text = textMain,
            fontSize = 16.sp,
            color = textColorMain,
            fontFamily = com.seryoga.sturmstorages.ui.theme.Font.tomorrowRegular,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            when (typeOfElement) {
                TypeOfElement.TWO_ELEMENTS -> {
                    if (content == null) {
                        Icon(
                            modifier = Modifier
                                .height(20.dp),
                            painter = painterResource(icon),
                            contentDescription = "",
                            tint = tint,
                        )
                    } else {
                        content()
                    }
                    Text(
                        text = text,
                        color = textColor,
                        fontSize = textSize.sp,
                        modifier = Modifier
//                        .background(Color.Blue)
                        ,
                        fontFamily = com.seryoga.sturmstorages.ui.theme.Font.tomorrowRegular,
                    )
                }

                TypeOfElement.ONE_ELEMENTS -> {
                    if (content == null) {
                        Icon(
                            modifier = Modifier
//                            .background(Color.Yellow)
                                .height(20.dp),
                            painter = painterResource(icon),
                            contentDescription = "",
                            tint = tint,
                        )
                    } else {
                        content()
                    }
                }
            }
        }
    }


}

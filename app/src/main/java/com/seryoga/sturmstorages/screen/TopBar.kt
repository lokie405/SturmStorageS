@file:Suppress("UNREACHABLE_CODE")

package com.seryoga.sturmstorages.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.seryoga.sturmstorages.R
import com.seryoga.sturmstorages.model.NavRoutes
import com.seryoga.sturmstorages.ui.theme.ColorGreen
import com.seryoga.sturmstorages.ui.theme.Font
import com.seryoga.sturmstorages.util.ViewModelProduct
import com.seryoga.sturmstorages.util.ViewModelSturm

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavHostController,
    vmProduct: ViewModelProduct,
    vmSturm: ViewModelSturm,
//    providerListInTopBar : List<String>,
    content : @Composable () -> Unit
//    listOfProviders: MutableState<List<String>>,
//    setList : () -> Unit
) {
    val context = LocalContext.current
//    Log.i(TAG, "--TopBar: START")
    var expanded by remember { mutableStateOf(false) }
//    var chosenProvider by remember { mutableStateOf("") }
//    val listOfProviders by viewModel.providers.observeAsState(initial = emptyList())


//    val searchProvider by viewModel.searchText.collectAsState()
//    val isSearching by viewModel.isSearching.collectAsState()
//    val providerList by viewModel.providersList.collectAsState()

    Row(
        modifier = Modifier
            .fillMaxHeight(),
        verticalAlignment = vmSturm.topVerticalAlignment
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth(0.2f)
                .height(vmSturm.topElementHeight),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "22/12",
                fontSize = 16.sp,
                fontFamily = Font.jetBrainMonoBold,
                color = ColorGreen
            )
        }
        content()
        Box(
            modifier = Modifier
                .height(vmSturm.topElementHeight)
                .fillMaxWidth()
            ,
            contentAlignment = Alignment.CenterEnd

            ,
        ) {
            IconButton(
                onClick = {
                    Toast.makeText(context,"Options", Toast.LENGTH_SHORT).show()
                    navController.navigate(NavRoutes.Setting.route)
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



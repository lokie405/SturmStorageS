package com.seryoga.sturmstorages.screen

import SettingStoreManager
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.seryoga.sturmstorages.R
import com.seryoga.sturmstorages.model.DataS
import com.seryoga.sturmstorages.model.DesignS
import com.seryoga.sturmstorages.model.DialogType
import com.seryoga.sturmstorages.model.DisplayS
import com.seryoga.sturmstorages.model.DisplayType
import com.seryoga.sturmstorages.model.NavRoutes
import com.seryoga.sturmstorages.model.Position
import com.seryoga.sturmstorages.model.SettingData
import com.seryoga.sturmstorages.screen.dialog.DialogToChoosen
import com.seryoga.sturmstorages.screen.dialog.DialogURL
import com.seryoga.sturmstorages.ui.theme.Font
import com.seryoga.sturmstorages.util.Const.TAG
import com.seryoga.sturmstorages.util.ViewModelProduct
import kotlinx.coroutines.launch


//@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SettingScreen(
    navController: NavController,
    settingStoreManager: SettingStoreManager = SettingStoreManager(LocalContext.current),
    vmProduct: ViewModelProduct = viewModel()
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var typeOfDialog by remember { mutableStateOf(DialogType.NO_DIALOG) }
    val settings by SettingStoreManager(context).settingsFlow.collectAsState(
        SettingData()
    )
//    Log.i(TAG, "setting.colorProviderBackground= ${settings.colorOfProviderBackground}")
//    var isProviderHeaderTypeChosen by remember { mutableStateOf(setting.displayType == DisplayType.PROVIDER_HEADER) }
//    Log.i(TAG, "IS PROVIDER@@: ${isProviderHeaderTypeChosen} ; set.dis:${setting.displayType}; Dis.PROV:${DisplayType.PROVIDER_HEADER}; eq:${setting.displayType == DisplayType.PROVIDER_HEADER}");
//    Log.i(TAG, "--SettingScreen: ===${settings.displayType}")
    var s_IconTheme by remember { mutableStateOf(R.drawable.setting_moon_icon) }
    var s_TextTheme by remember { mutableStateOf(R.string.setting_theme_dark) }
    var s_DisplayType by remember { mutableStateOf(R.string.setting_display_type_all_in_row) }
    var s_HryvniaSign by remember { mutableStateOf(R.string.setting_hide_hryvnia_sign) }
    var s_IconAutoupdate by remember { mutableStateOf(R.drawable.setting_autoupdate_allow) }
    var s_TextAutoupdate by remember { mutableStateOf(R.string.setting_autoupdate_allow) }

    s_TextTheme = DisplayS.getNameTheme(settings.themeType)
    s_IconTheme = DisplayS.getIconTheme(settings.themeType)
    s_DisplayType = DisplayS.getNameDisplay(settings.displayType)
    s_HryvniaSign = DisplayS.getNameHryvniaSign(settings.hryvniaSign)
    s_IconAutoupdate = DataS.getIconIsAutoupdate(settings.isAutoupdate)
    s_TextAutoupdate = DataS.getNameIsAutoupdate(settings.isAutoupdate)
    Log.i(TAG, "autoupdate = ${settings.isAutoupdate}")
    Scaffold(
        topBar = {
            ScreenTitleMain(
                stringResource(R.string.setting_title),
                onClick = {
                    navController.navigate(NavRoutes.Main.route) {
                        launchSingleTop = true
                    }
                },
            )
        }
    ) { innerPadding ->
        Card(
            modifier = Modifier
                .fillMaxSize()
        ) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(start = 10.dp, end = 10.dp)
            ) {
                item {

// ---display type
                    SettingTitle(stringResource(R.string.setting_display))
                    // ---theme light/dark
                    SettingItem(
                        Position.TOP,
                        painterResource(s_IconTheme),
                        stringResource(R.string.setting_theme),
                        stringResource(s_TextTheme),
                        onClick = {
                            coroutineScope.launch {
                                settingStoreManager.toggleAndSaveThemeType()
                            }
                        }
                    )
                    // ---display type
                    SettingItem(
                        Position.MIDDLE,
                        painterResource(R.drawable.setting_display_type_icon),
                        stringResource(R.string.setting_display_type),
                        stringResource(s_DisplayType),
                        onClick = {
                            coroutineScope.launch {
                                settingStoreManager.toggleAndSaveDisplayType()
                            }
                        }
                    )
                    //  ---hryvnia sign
                    SettingItem(
                        Position.MIDDLE,
                        painterResource(R.drawable.hryvnia_sign_icon),
                        stringResource(R.string.setting_hryvnia_sign),
                        stringResource(s_HryvniaSign),
                        onClick = {
                            coroutineScope.launch {
                                settingStoreManager.toggleAndSaveHryvniaSign()
                            }
                        }
                    )

//  ---data
                    SettingTitle(stringResource(R.string.setting_data))
                    SettingItem(
                        Position.TOP,
                        icon = painterResource(R.drawable.compass_icon),
                        stringResource(R.string.setting_url),
                        onClick = {
                            typeOfDialog = DialogType.URL
                        }
                    )

                    //  ---dialog url
                    if (typeOfDialog == DialogType.URL) {

                        var url by remember { mutableStateOf(settings.url) }
                        val clipboardManager = LocalClipboardManager.current

                        DialogToChoosen(
                            stringResource(R.string.setting_url),
                            onDismissClick = { typeOfDialog = DialogType.NO_DIALOG },
                            onConfirmClick = {
                                coroutineScope.launch {
                                    settingStoreManager.saveURL(url)
                                }
                                typeOfDialog = DialogType.NO_DIALOG
                            },
                            content = {
                                DialogURL(
                                    reset = {
                                        url = DataS.default.getValue(DataS.URL_ID) as String
                                    },
                                    clear = { url = "" },
                                    past = {
                                        val pastText = clipboardManager.getText()?.text
                                        if (pastText != null) {
                                            url = pastText
                                        } else url = DataS.default.getValue(DataS.URL_ID) as String
                                    },
                                ) {
                                    OutlinedTextField(
                                        value = url,
                                        onValueChange = { url = it }
                                    )

                                }
                            }
                        )
                    }

                    //  ---Autoupdate
//                    var isAutoupdate =
                    SettingItem(
                        position = Position.MIDDLE,
                        painterResource(s_IconAutoupdate),
                        stringResource(R.string.setting_autoupdate),
                        stringResource(s_TextAutoupdate),
                        onClick = {
                            coroutineScope.launch {

                                settingStoreManager.toggleAndSaveIsAutoupdate()
//                                    val vmProduct: ViewModelProduct = viewModel()
                                vmProduct.isLoad = false
                            }
                        }

                    )
//  ---row settings


                    SettingTitle(stringResource(R.string.setting_row_setting))
                    //  ---design of product
                    SettingItem(
                        Position.TOP,
                        painterResource(R.drawable.design_icon),
                        stringResource(R.string.setting_design_of_product),
                        onClick = {
                            navController.navigate(NavRoutes.DesignPicker.passRoot(DesignS.PRODUCT_DESIGN))
                        }
                    )
                    SettingItem(
                        Position.MIDDLE,
                        painterResource(R.drawable.design_icon),
                        stringResource(R.string.setting_design_of_product),
                        onClick = {
                            navController.navigate(NavRoutes.DesignPicker.passRoot(DesignS.PRODUCT_DESIGN))
                        }
                    )
                    //  ---design of price
                    SettingItem(
                        Position.MIDDLE,
                        painterResource(R.drawable.design_icon),
                        stringResource(R.string.setting_design_of_price),
                        onClick = {
                            navController.navigate(NavRoutes.DesignPicker.passRoot(DesignS.PRICE_DESIGN))
                        }
                    )
                    //  ---design of quantity
                    SettingItem(
                        Position.MIDDLE,
                        painterResource(R.drawable.design_icon),
                        stringResource(R.string.setting_design_of_quantity),
                        onClick = {
                            navController.navigate(NavRoutes.DesignPicker.passRoot(DesignS.QUANTITY_DESIGN))
                        }
                    )
                    //  ---design of provider
                    SettingItem(
                        Position.MIDDLE,
                        painterResource(R.drawable.design_icon),
                        stringResource(R.string.setting_design_of_provider),
                        onClick = {
                            navController.navigate(NavRoutes.DesignPicker.passRoot(DesignS.PROVIDER_DESIGN))
                        }
                    )
                    //  ---color of provider different
                    if (settings.displayType != DisplayType.PROVIDER_HEADER) {
                        SettingItem(
                            Position.MIDDLE,
                            painterResource(R.drawable.brush_icon),
                            stringResource(R.string.setting_color_of_provider_second),
                            onClick = {
                                navController.navigate(NavRoutes.DesignPicker.passRoot(DesignS.PROVIDER_SECOND_DESIGN))
                            }
                        )
                    }
                    //  ---color of background provider header
                    if (settings.displayType == DisplayType.PROVIDER_HEADER) {
                        SettingItem(
                            Position.MIDDLE,
                            painterResource(R.drawable.brush_icon),
                            stringResource(R.string.setting_color_of_provider_background),
                            onClick = {
                                navController.navigate(NavRoutes.DesignPicker.passRoot(DesignS.COLOR_OF_PROVIDER_BACKGROUND_ID))
                            }
                        )
                    }
                    //  ---color of row background
                    SettingItem(
                        Position.MIDDLE,
                        painterResource(R.drawable.brush_icon),
                        stringResource(R.string.setting_color_of_row_background),
                        onClick = {
                            navController.navigate(NavRoutes.DesignPicker.passRoot(DesignS.COLOR_OF_ROW_BACKGROUND_ID))
                        }
                    )
                    //  ---color of active row background
                    SettingItem(
                        Position.MIDDLE,
                        painterResource(R.drawable.brush_icon),
                        stringResource(R.string.setting_color_of_row_background_active),
                        onClick = {
                            navController.navigate(NavRoutes.DesignPicker.passRoot(DesignS.COLOR_OF_ROW_BACKGROUND_ACTIVE_ID))
                        }
                    )

                    //  ---reset all design to default
                    SettingItem(
                        Position.BOTTOM,
                        painterResource(R.drawable.refresh_clock_icon),
                        stringResource(R.string.setting_reset_design_to_default),
                        onClick = {
                            typeOfDialog = DialogType.RESET_DESIGN
                        }
                    )

                    //  ---dialog to reset
                    if (typeOfDialog == DialogType.RESET_DESIGN) {
                        DialogToChoosen(
                            stringResource(R.string.confirm_reset_design),
                            onDismissClick = { typeOfDialog = DialogType.NO_DIALOG },
                            onConfirmClick = {
                                coroutineScope.launch {
                                    DesignS.resetDesignToDefault(context)
                                }
                                typeOfDialog = DialogType.NO_DIALOG
                            },
                        )
                    }


                }
            }
        }
    }
}


@Composable
fun SettingTitle(title: String) {
    Text(
        modifier = Modifier
            .padding(start = 20.dp),
        text = title,
        fontFamily = Font.jetBrainMonoBold,
//        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = MaterialTheme.colorScheme.onPrimary

    )
}

@Composable
fun SettingItem(
    position: Position,
    icon: Painter,
    title: String,
    chosen: String = "",
    content: @Composable () -> Unit = {},
    onClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .then(
                when (position) {
                    Position.TOP -> Modifier.padding(top = 10.dp)
                    Position.MIDDLE -> Modifier
                    Position.BOTTOM -> Modifier.padding(bottom = 20.dp)
                }
            )
            .fillMaxWidth()
            .then(
                when (position) {
                    Position.TOP -> Modifier.clip(
                        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                    )

                    Position.MIDDLE -> Modifier
                    Position.BOTTOM -> Modifier.clip(
                        shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
                    )
                }
            )
            .background(MaterialTheme.colorScheme.secondary)
            .clickable { onClick() }
    ) {
        Row(
            Modifier
                .height(70.dp)
                .padding(horizontal = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, title, tint = MaterialTheme.colorScheme.tertiary)
            Spacer(Modifier.width(14.dp))
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontFamily = Font.jetBrainMonoBold,
                    color = MaterialTheme.colorScheme.tertiary
                )
                Spacer(modifier = Modifier.height(4.dp))
                if (!chosen.isEmpty()) {
                    Text(
                        text = chosen,
                        fontSize = 14.sp,
                        fontFamily = Font.jetBrainMonoMedium,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }
            }
            content()
        }
    }
}

//@Composable
//fun SettingItem(icon: Painter, title: String, content: @Composable () -> Unit) {
//    Row(
//        Modifier.padding(vertical = 4.dp, horizontal = 10.dp),
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Icon(icon, title)
//        Spacer(Modifier.width(10.dp))
//        Column()
//        Text(
//            text = title,
//            fontSize = 16.sp
//        )
//        content()
//    }
//}

//@Composable
//fun SettingChose(name: String, onClick: () -> Unit) {
//    IconButton(onClick = onClick) {
//        Text(name)
//    }
//}

//@Composable
//fun SettingItemTop(
//    themeColors: Theme.Colors,
//    icon: Painter,
//    title: String,
//    chosen: String = "",
//    content: @Composable () -> Unit = {},
//    onClick: () -> Unit = {},
//) {
//    Box(
//        modifier = Modifier
//            .padding(top = 10.dp)
//            .fillMaxWidth()
//            .clip(shape = RoundedCornerShape(8.dp, 8.dp, 0.dp, 0.dp))
//            .background(themeColors.secondBackgroundColor)
//            .clickable { onClick() }
//    ) {
//        SettingItem(themeColors, icon, title, chosen, content, onClick)
//    }
//}
//
//@Composable
//fun SettingItemMiddle(
//    themeColors: Theme.Colors,
//    icon: Painter,
//    title: String,
//    chosen: String = "",
//    content: @Composable () -> Unit = {},
//    onClick: () -> Unit = {},
//) {
//    Box(
//        modifier = Modifier
////            .padding(top = 10.dp)
//            .fillMaxWidth()
////            .clip(shape = RoundedCornerShape(8.dp, 8.dp, 0.dp, 0.dp))
//            .background(themeColors.secondBackgroundColor),
//
//        ) {
//        SettingItem(themeColors, icon, title, chosen, content, onClick)
//    }
//}
//
//@Composable
//fun SettingItemBottom(
//    themeColors: Theme.Colors,
//    icon: Painter,
//    title: String,
//    chosen: String = "",
//    content: @Composable () -> Unit = {},
//    onClick: () -> Unit = {},
//) {
//    Box(
//        modifier = Modifier
//            .padding(bottom = 10.dp)
//            .fillMaxWidth()
//            .clip(shape = RoundedCornerShape(0.dp, 0.dp, 8.dp, 8.dp))
//            .background(themeColors.secondBackgroundColor),
//
//        ) {
//        SettingItem(themeColors, icon, title, chosen, content, onClick)
//    }
//}

@Composable
fun SettingTitleSpacer(spacer: Int) {
    Spacer(modifier = Modifier.height(spacer.dp))
}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    SettingScreen(NavController(LocalContext.current))
}


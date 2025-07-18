package com.seryoga.sturmstorages.model

import SettingStoreManager
import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.seryoga.sturmstorages.R
import com.seryoga.sturmstorages.screen.AllInRow
import com.seryoga.sturmstorages.screen.AllInRowAtCell
import com.seryoga.sturmstorages.screen.IconInListClicked
import com.seryoga.sturmstorages.screen.ProviderHeader
import com.seryoga.sturmstorages.screen.ScreenTitleMain
import com.seryoga.sturmstorages.screen.ScreenTitleText
import com.seryoga.sturmstorages.screen.ScreenTitleTextClicked
import com.seryoga.sturmstorages.screen.SpacerS
import com.seryoga.sturmstorages.util.Const.testItem
import com.seryoga.sturmstorages.util.ViewModelProduct

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RowDisplaySetting(
    navController: NavHostController,
//    settings: SettingData,
    vmProduct: ViewModelProduct,
) {

    val settingStoreManager = SettingStoreManager(LocalContext.current)
    val scope = rememberCoroutineScope()
    var expandedDecorChosen by remember { mutableStateOf(false) }
    val itemToDesign = vmProduct.itemToDesign.collectAsState()
    var displayType by remember { mutableStateOf(DisplayType.ALL_IN_ROW) }


    Log.i("MyLog", "decoratiItem: ${itemToDesign.value}");


    Scaffold(
        topBar = {
            ScreenTitleMain(
                content = {
                    ScreenTitleText(
                        stringResource(DesignS.titleAndIcons.getValue(itemToDesign.value)[0]),
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 10.dp),
                    ) {

                        IconButton(
                            onClick = {
                                expandedDecorChosen = !expandedDecorChosen
                            }
                        ) {
                            Icon(
                                painter = when (expandedDecorChosen) {
                                    false -> painterResource(R.drawable.arrow_down)
                                    true -> painterResource(R.drawable.arrow_up)
                                },
                                tint = MaterialTheme.colorScheme.onPrimary,
                                contentDescription = stringResource(R.string.expandable_button)
                            )
                        }



                        DropdownMenu(
                            modifier = Modifier,
                            offset = DpOffset(x = 0.dp, y = 10.dp),
                            expanded = expandedDecorChosen,
                            onDismissRequest = { expandedDecorChosen = false }
                        ) {
                            DesignS.titleAndIcons.forEach { (key, list) ->
                                DropdownMenuItem(

                                    text = {
                                        ScreenTitleTextClicked(
                                            list = list,
                                            isSelected = itemToDesign.value == key,
                                            fontSize = 20,
                                            onClick = {
                                                 vmProduct.setItemToDesign(key)
                                                Log.i("MyLog", "title - ${itemToDesign}");
                                                expandedDecorChosen = false
                                            }
                                        )
                                    },
                                    onClick = {
                                    }
                                )
                            }
                        }
                    }
                },
                onClickBack = {
                    navController.popBackStack()
                    navController.navigate(NavRoutes.Setting.route) {
                        launchSingleTop = true
                    }
                }
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .navigationBarsPadding()
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    DesignS.titleAndIcons.keys.toList().forEach { key ->
                        Box(
                            modifier = Modifier
                                .padding(10.dp)
                                .clickable {
                                    vmProduct.setItemToDesign(key)
                                    Log.i("MyLog", "title - ${itemToDesign}")
                                    expandedDecorChosen = false
                                }
                        ) {
                            IconInListClicked(
                                list = DesignS.titleAndIcons.getValue(key),
                                isSelected = itemToDesign.value == key,
                                onClick = {
                                    vmProduct.setItemToDesign(key)
                                    expandedDecorChosen = false
                                }
                            )
                        }

                    }
                }
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Box(modifier = Modifier.animateContentSize()) {
                when (displayType) {
                    DisplayType.ALL_IN_ROW -> {
                    Log.i("MyLog", "displayType: ${displayType}");
//                        Log.i("MyLog", "currCol = ${currentColor.toHex()}");
                        AllInRow(
//                            settings,
                            listOf(testItem),
//                            settingDesign = SettingDesign(
//                                name = decorateItem,
//                                color = if (decorateItem == DesignS.PROVIDER_DESIGN && isDifferentProvider) {
//                                    currentDiffProviderColor.toArgb()
//                                } else currentColor.toArgb(),
//                                size = currentFontSize,
//                                font = Font.mapFontsFamily[currentFontFamily],
//                                backgroundColor = currentBackgroundColor.toArgb(),
////                                decoration = currentTextDecorationOfHighlight,
//                            ),
                            vmProduct
                        )
                    }

                    DisplayType.ALL_IN_ROW_AT_CELL -> {
                    Log.i("MyLog", "displayType: ${displayType}");
                        AllInRowAtCell(
//                            settings,
                            listOf(testItem),
//                            settingDesign = SettingDesign(
//                                name = decorateItem,
//                                color = currentColor.toArgb(),
//                                size = currentFontSize,
//                                font = Font.mapFontsFamily[currentFontFamily],
//                                backgroundColor = currentBackgroundColor.toArgb(),
////                                decoration = currentTextDecorationOfHighlight,
//                            ),
                            vmProduct
                        )
                    }

                    DisplayType.PROVIDER_HEADER -> {
                    Log.i("MyLog", "displayType: ${displayType}");
                        ProviderHeader(
//                            settings,
                            listOf(testItem).groupBy { it.provider },
////                            settingDesign = SettingDesign(
////                                name = decorateItem,
////                                color = currentColor.toArgb(),
////                                size = currentFontSize,
////                                font = Font.mapFontsFamily[currentFontFamily],
////                                backgroundColor = currentBackgroundColor.toArgb(),
//////                                decoration = currentTextDecorationOfHighlight,
////                            ),
                            vmProduct
                        )
                    }
                }
            }

            SpacerS(20)
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                IconButton(
                    onClick = { displayType = DisplayType.ALL_IN_ROW }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.number_1_square_icon),
                        contentDescription = stringResource(R.string.setting_display_type_all_in_row),
                        tint = if (displayType == DisplayType.ALL_IN_ROW) {
                            MaterialTheme.colorScheme.onTertiary
                        } else MaterialTheme.colorScheme.onPrimary
                    )
                }
                IconButton(
                    onClick = { displayType = DisplayType.ALL_IN_ROW_AT_CELL }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.number_2_square_icon),
                        contentDescription = stringResource(R.string.setting_display_type_all_in_row_at_cell),
                        tint = if (displayType == DisplayType.ALL_IN_ROW_AT_CELL) {
                            MaterialTheme.colorScheme.onTertiary
                        } else MaterialTheme.colorScheme.onPrimary
                    )
                }
                IconButton(
                    onClick = { displayType = DisplayType.PROVIDER_HEADER }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.number_3_square_icon),
                        contentDescription = stringResource(R.string.setting_display_type_provider_header),
                        tint = if (displayType == DisplayType.PROVIDER_HEADER) {
                            MaterialTheme.colorScheme.onTertiary
                        } else MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
            SpacerS(20)
            key(itemToDesign, displayType) {

//  ---  ---
                LazyColumn {



                }
            }
        }
    }
}


fun invertColor(backgroundColor: Color): Color {
    val textColor: Int by lazy(LazyThreadSafetyMode.NONE) {
        if (backgroundColor.luminance() > 0.5) {
            Color.Black.toArgb()
        } else {
            Color.White.toArgb()
        }
    }
    return Color(textColor)
}

fun Color.toHex(): String {
    val red = (red * 255).toInt().coerceIn(0, 255)
    val green = (green * 255).toInt().coerceIn(0, 255)
    val blue = (blue * 255).toInt().coerceIn(0, 255)
    val alpha = (alpha * 255).toInt().coerceIn(0, 255)

    return "#%02X%02X%02X%02X".format(alpha, red, green, blue)
}
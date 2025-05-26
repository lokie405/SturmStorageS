package com.seryoga.sturmstorages.screen

import SettingStoreManager
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.github.skydoves.colorpicker.compose.AlphaSlider
import com.github.skydoves.colorpicker.compose.AlphaTile
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.seryoga.sturmstorages.R
import com.seryoga.sturmstorages.db.Product
import com.seryoga.sturmstorages.model.ButtonType
import com.seryoga.sturmstorages.model.DesignS
import com.seryoga.sturmstorages.model.DisplayType
import com.seryoga.sturmstorages.model.NavRoutes
import com.seryoga.sturmstorages.model.RootS
import com.seryoga.sturmstorages.model.SettingData
import com.seryoga.sturmstorages.model.SettingDesign
import com.seryoga.sturmstorages.ui.theme.Font
import com.seryoga.sturmstorages.util.Const.TAG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DesignPicker(
    navController: NavHostController,
    settings: SettingData,
    root: String,
) {


    val settingStoreManager = SettingStoreManager(LocalContext.current)
    Log.i(TAG, "--- root in DesignPicker ${root}");
    var isOnlyColorDesign by remember { mutableStateOf(false) }
//    val setting by SettingStoreManager(LocalContext.current).settingsFlow.collectAsState(SettingData())
    val scope = rememberCoroutineScope()

    when (root) {
        DesignS.PRODUCT_DESIGN -> {
            isOnlyColorDesign = false
            RootS.title = stringResource(R.string.setting_design_of_product)
            RootS.oidColor = Color(settings.colorOfProduct)
            RootS.oldFontSize = settings.fontSizeOfProduct
            RootS.oldFontFamily = settings.fontFamilyOfProduct
        }
        DesignS.PRICE_DESIGN -> {
            isOnlyColorDesign = false
            RootS.title = stringResource(R.string.setting_design_of_price)
            RootS.oidColor = Color(settings.colorOfPrice)
            RootS.oldFontSize = settings.fontSizeOfPrice
            RootS.oldFontFamily = settings.fontFamilyOfPrice
        }
        DesignS.QUANTITY_DESIGN -> {
            isOnlyColorDesign = false
            RootS.title = stringResource(R.string.setting_design_of_quantity)
            RootS.oidColor = Color(settings.colorOfQuantity)
            RootS.oldFontSize = settings.fontSizeOfQuantity
            RootS.oldFontFamily = settings.fontFamilyOfQuantity
        }
        DesignS.PROVIDER_DESIGN -> {
            isOnlyColorDesign = false
            RootS.title = stringResource(R.string.setting_design_of_provider)
            RootS.oidColor = Color(settings.colorOfProvider)
            RootS.oldFontSize = settings.fontSizeOfProvider
            RootS.oldFontFamily = settings.fontFamilyOfProvider
        }
        DesignS.PROVIDER_SECOND_DESIGN -> {
            isOnlyColorDesign = true
            RootS.title = stringResource(R.string.setting_color_of_provider_second)
        }
        DesignS.COLOR_OF_PROVIDER_BACKGROUND_ID -> {
            isOnlyColorDesign = true
            RootS.title = stringResource(R.string.setting_color_of_provider_background)
            RootS.oidColor = Color(settings.colorOfProviderBackground)
        }
        DesignS.COLOR_OF_ROW_BACKGROUND_ID -> {
            isOnlyColorDesign = true
            RootS.title = stringResource(R.string.setting_color_of_row_background)
            RootS.oidColor = Color(settings.colorOfRowBackground)
        }
        DesignS.COLOR_OF_ROW_BACKGROUND_ACTIVE_ID -> {
            isOnlyColorDesign = true
            RootS.title = stringResource(R.string.setting_color_of_row_background_active)
            RootS.oidColor = Color(settings.colorOfRowBackgroundActive)
        }
        else -> Color.Transparent
    }

    var currentColor by remember { mutableStateOf(RootS.oidColor) }
    var currentFontSize by remember { mutableStateOf(RootS.oldFontSize) }
    var currentFontFamily by remember { mutableStateOf(RootS.oldFontFamily) }
    /* 0 */
//    var currentProviderBackground by remember { mutableStateOf(RootS.oldProviderBackground) }

    val controller = rememberColorPickerController()
    var hexOfCurrentColor by remember { mutableStateOf(currentColor.toHex()) }

    val testItem: Product = Product(
        id = 3333,
        name = "CC9940CL Пилка ланцюгова акумуляторна 16\" 40В STURM, арт. 18614 (шт.)",
        price = "5'648.00 грн.",
        quantity = "12.000",
        provider = "УЗП - Електроiнструмент",
        date = "00/00"
    )

    Log.i(TAG, "RootsOld Color: ${RootS.oidColor.toHex()}");
    
    Scaffold(
        topBar = {
            ScreenTitleMain(
//                navController,
                RootS.title,
                onClick = {
                    navController.navigate(NavRoutes.Setting.route) {
                        launchSingleTop = true
                    }
                }
            )
        },
        bottomBar = {
            ButtonBar(
                navController,
                scope,
                settingStoreManager,
                root,
                currentColor,
                currentFontSize,
                currentFontFamily,
                isOnlyColorDesign,
            )
        }
    ) { innerPadding ->

//  ---start compose

//        Log.d("ScaffoldPadding", "Top: ${innerPadding.calculateTopPadding()}, " +
//            "Bottom: ${innerPadding.calculateBottomPadding()}, " +
//            "Start: ${innerPadding.calculateStartPadding(LayoutDirection.Ltr)}, " +
//            "End: ${innerPadding.calculateEndPadding(LayoutDirection.Ltr)}")
        Column(modifier = Modifier.padding(innerPadding))
        {


//            SpacerS(30)

            when (settings.displayType) {
                DisplayType.ALL_IN_ROW -> {

                    AllInRow(
                        settings,
                        listOf(testItem),
                        settingDesign = SettingDesign(
                            name = root,
                            color = currentColor.toArgb(),
                            size = currentFontSize,
                            font = Font.mapFontsFamily[currentFontFamily]
                        ),
                    )
                }

                DisplayType.ALL_IN_ROW_AT_CELL -> {
                    AllInRowAtCell(
                        settings,
                        listOf(testItem),
                        settingDesign = SettingDesign(
                            name = root,
                            color = currentColor.toArgb(),
                            size = currentFontSize,
                            font = Font.mapFontsFamily[currentFontFamily],
                        )
                    )
                }

                DisplayType.PROVIDER_HEADER -> {

                    ProviderHeader(
                        settings,
                        listOf(testItem).groupBy { it.provider },
                        settingDesign = SettingDesign(
                            name = root,
                            color = currentColor.toArgb(),
                            size = currentFontSize,
                            font = Font.mapFontsFamily[currentFontFamily],
                        )
                    )

                }
            }
            SpacerS(20)
            LazyColumn {

//  ---color
                stickyHeader {
                    DesignTitle(stringResource(R.string.color))
                }
                item {
                    SpacerS(20)
                    HsvColorPicker(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(top = 0.dp, start = 10.dp, end = 10.dp, bottom = 10.dp),
                        onColorChanged = { colorEnvelope: ColorEnvelope ->

                            currentColor = colorEnvelope.color // ARGB color value.
                            Log.i(TAG, "Current color: ${currentColor}; # ${currentColor.toHex()}");
                            Log.i(TAG, "HEX color: ${colorEnvelope.color.toHex()};");
                            hexOfCurrentColor = currentColor.toHex()
                        },
                        controller = controller,
                        initialColor = RootS.oidColor,
                    )
                    AlphaSlider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .height(35.dp)
                            .border(
                                1.dp,
                                MaterialTheme.colorScheme.onPrimary,
                                RoundedCornerShape(6.dp)
                            ),
                        initialColor = RootS.oidColor,
                        controller = controller,
                    )
                    BrightnessSlider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .height(35.dp)
                            .border(
                                1.dp,
                                MaterialTheme.colorScheme.onPrimary,
                                RoundedCornerShape(6.dp)
                            ),
                        initialColor = RootS.oidColor,
                        controller = controller,
                    )

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        AlphaTile(
                            modifier = Modifier
                                .width(200.dp)
                                .padding(top = 10.dp)
                                .height(40.dp)
                                .clip(RoundedCornerShape(6.dp))

                                .border(
                                    1.dp,
                                    invertColor(currentColor),
                                    RoundedCornerShape(6.dp)
                                ),

                            controller = controller,
                        )
                        Text(
                            modifier = Modifier
                                .padding(top = 12.dp),
                            text = hexOfCurrentColor,
                            color = invertColor(currentColor)
                        )
                    }
                    SpacerS(20)
                }
                if (!isOnlyColorDesign) {

//  ---font size
                    stickyHeader {
                        DesignTitle(stringResource(R.string.font_size))
                    }
                    item {
                        SpacerS(20)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center

                        ) {
                            ButtonWithIcon(
                                ButtonType.SMALL,
                                R.drawable.plus_icon,
                                onClick = {
                                    if (currentFontSize < 20) currentFontSize = ++currentFontSize
                                    scope.launch {
                                        settingStoreManager.saveFontSize(
                                            root,
                                            currentFontSize
                                        )
                                    }
                                }
                            )

                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 20.dp),
                                text = currentFontSize.toString(),
                                fontSize = 20.sp,
                                fontFamily = Font.jetBrainMonoMedium
                            )
                            ButtonWithIcon(
                                ButtonType.SMALL,
                                R.drawable.minus_icon,
                                onClick = {
                                    if (currentFontSize > 0) currentFontSize = --currentFontSize
                                    scope.launch {
                                        settingStoreManager.saveFontSize(
                                            root,
                                            currentFontSize
                                        )
                                    }
                                }
                            )
                        }
                        SpacerS(20)
                    }
//  ---font family
                    stickyHeader {
                        DesignTitle(stringResource(R.string.font_family))
                    }
                    item {
                        SpacerS(20)
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            ButtonTextS(ButtonType.SMALL,
                                stringResource(R.string.roboto),
                                fontFamily = Font.robotoMedium,
                                color = if (RootS.oldFontFamily == Font.ROBOTO) {
                                    MaterialTheme.colorScheme.onPrimary
                                } else MaterialTheme.colorScheme.onSecondary,
                                onClick = {
                                    currentFontFamily = Font.ROBOTO
                                    scope.launch {
                                        settingStoreManager.saveFontFamily(
                                            root,
                                            currentFontFamily
                                        )
                                    }
                                }
                            )
                            SpacerS(20)
                            ButtonTextS(ButtonType.SMALL,
                                stringResource(R.string.jet_brain),
                                fontFamily = Font.robotoMedium,
                                color = if (RootS.oldFontFamily == Font.JET_BRAIN) {
                                    MaterialTheme.colorScheme.onPrimary
                                } else MaterialTheme.colorScheme.onSecondary,
                                onClick = {
                                    currentFontFamily = Font.JET_BRAIN
                                    scope.launch {
                                        settingStoreManager.saveFontFamily(
                                            root,
                                            currentFontFamily
                                        )
                                    }
                                }
                            )
                            SpacerS(20)
                            ButtonTextS(ButtonType.SMALL,
                                stringResource(R.string.comic_relief),
                                fontFamily = Font.comicReliefRegular,
                                color = if (RootS.oldFontFamily == Font.COMIC_RELIEF) {
                                    MaterialTheme.colorScheme.onPrimary
                                } else {
                                    MaterialTheme.colorScheme.onSecondary
                                },
                                onClick = {
                                    currentFontFamily = Font.COMIC_RELIEF
                                    scope.launch {
                                        settingStoreManager.saveFontFamily(
                                            root,
                                            currentFontFamily
                                        )
                                    }
                                }
                            )
                            SpacerS(20)
                            ButtonTextS(ButtonType.SMALL,
                                stringResource(R.string.sans_narrow),
                                fontFamily = Font.sansNarrowRegular,
                                color = if (RootS.oldFontFamily == Font.SANS_NARROW) {
                                    MaterialTheme.colorScheme.onPrimary
                                } else MaterialTheme.colorScheme.onSecondary,
                                onClick = {
                                    currentFontFamily = Font.SANS_NARROW
                                    scope.launch {
                                        settingStoreManager.saveFontFamily(
                                            root,
                                            currentFontFamily
                                        )
                                    }
                                }
                            )
                            SpacerS(20)
                        }
                    }
                }
            }
            SpacerS(100)
        }
        BackHandler {
            scope.launch {
                settingStoreManager.saveColor(
                    root,
                    RootS.oidColor.toArgb()
                )
                navController.popBackStack()
            }

        }
    }
}

//  ---buttons for save or escape
@Composable
fun ButtonBar(
    navController: NavHostController,
    scope: CoroutineScope,
    settingStoreManager: SettingStoreManager,
    root: String,
    currentColor: Color,
    currentFontSize: Int,
    currentFontFamily: String,
    isOnlyColorDesign: Boolean,
) {
    Row(
        modifier = Modifier
            .padding(bottom = 50.dp, top = 20.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        ButtonWithIcon(
            ButtonType.MEDIUM,
            R.drawable.clear_icon,
            onClick = {
                scope.launch {
                    settingStoreManager.saveColor(root, RootS.oidColor.toArgb())
                    if (!isOnlyColorDesign) {
                        settingStoreManager.saveFontSize(root, RootS.oldFontSize)
                        settingStoreManager.saveFontFamily(root, RootS.oldFontFamily)
                    }
                }
                navController.popBackStack()
            })

        ButtonWithIcon(
            ButtonType.MEDIUM,
            R.drawable.ok_icon,
            onClick = {
//                Log.i(TAG, "SAVE to ${root}; with current fontfamily: ${currentColor.toArgb()}");
                scope.launch {
                    settingStoreManager.saveColor(root, currentColor.toArgb())
                    if (!isOnlyColorDesign) {
                        settingStoreManager.saveFontSize(root, currentFontSize)
                        settingStoreManager.saveFontFamily(root, currentFontFamily)
                    }
                    navController.popBackStack()
                }
            }
        )

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
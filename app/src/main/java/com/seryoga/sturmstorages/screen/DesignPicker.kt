package com.seryoga.sturmstorages.screen

import SettingStoreManager
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
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
import com.seryoga.sturmstorages.model.RootS
import com.seryoga.sturmstorages.model.SettingData
import com.seryoga.sturmstorages.model.SettingDesign
import com.seryoga.sturmstorages.ui.theme.Font
import com.seryoga.sturmstorages.util.Const
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
//    Log.i(TAG, "--- root ${root}");

//    val setting by SettingStoreManager(LocalContext.current).settingsFlow.collectAsState(SettingData())
    val scope = rememberCoroutineScope()

    when (root) {
        DesignS.PRODUCT_DESIGN -> {
            RootS.title = stringResource(R.string.setting_design_of_product)
            RootS.oidColor = Color(settings.colorOfProduct)
            RootS.oldFontSize = settings.fontSizeOfProduct
        }

        DesignS.PRICE_DESIGN -> {
            RootS.title = stringResource(R.string.setting_design_of_price)
            RootS.oidColor = Color(settings.colorOfPrice)
            RootS.oldFontSize = settings.fontSizeOfPrice
        }

        DesignS.QUANTITY_DESIGN -> {
            RootS.title = stringResource(R.string.setting_design_of_quantity)
            RootS.oidColor = Color(settings.colorOfQuantity)
            RootS.oldFontSize = settings.fontSizeOfQuantity
        }

        DesignS.PROVIDER_DESIGN -> {
            RootS.title = stringResource(R.string.setting_design_of_provider)
            RootS.oidColor = Color(settings.colorOfProvider)
            RootS.oldFontSize = settings.fontSizeOfProvider
        }

        DesignS.PROVIDER_SECOND_DESIGN -> {
            RootS.title = stringResource(R.string.setting_design_of_provider_second)
            RootS.oidColor = Color(settings.colorOfProviderSecond)
            RootS.oldFontSize = settings.fontSizeOfProvider

        }

        else -> Color.Transparent
    }

    var currentColor by remember { mutableStateOf(RootS.oidColor) }
    var currentFontSize by remember { mutableStateOf(RootS.oldFontSize) }
    val controller = rememberColorPickerController()
    var hexOfCurrentColor by remember { mutableStateOf(currentColor.toHex()) }

    val testItem: Product = Product(
        id = 3333,
        name = "CC9940CL Пилка ланцюгова акумуляторна 16\" 40В STURM, арт. 18614 (шт.)",
        price = "5'648.00 грн.",
        quantity = "12.000",
        provider = "УЗП - Електроiнструмент"
    )

    Scaffold(
        bottomBar = { ButtonBar(navController, scope, settingStoreManager, root, currentColor, currentFontSize) }
    ) { innerPadding ->

//  ---start compose
//
//        {
        Column(modifier = Modifier.padding(innerPadding))
        {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = RootS.title,
                fontSize = 22.sp,
                fontFamily = Font.jetBrainMonoBold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary
            )
            SpacerS(30)

            when (settings.displayType) {
                DisplayType.ALL_IN_ROW -> {

                    Column {
                        ItemProductAllInRow(
                            settings,
                            testItem,
                            if (root == DesignS.COLOR_OF_PROVIDER_ID || root == DesignS.COLOR_OF_PROVIDER_SECOND_ID) {
                                Color(currentColor.toArgb())
                            } else {
                                Const.COLOR_PROVIDER_1
                            },
                            settingDesign = SettingDesign(
                                name = root,
                                color = currentColor.toArgb(),
                                size = currentFontSize
//                                TODO: continue...
                            )
                        )
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .padding(horizontal = 4.dp)
                                .background(
                                    color =
                                    when (root) {
                                        DesignS.PROVIDER_DESIGN -> Color(currentColor.toArgb())
                                        DesignS.PROVIDER_SECOND_DESIGN -> Color(
                                            currentColor.toArgb()
                                        )

                                        else -> Color(settings.colorOfProduct)
                                    }
                                ),
                        )
                    }
                }
            }
            SpacerS(20)
            LazyColumn {

//  ---color
                stickyHeader {
                    DesignTitle(stringResource(R.string.color))
                }
                item {
//                    Card() {
                    SpacerS(20)
                    HsvColorPicker(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(top = 0.dp, start = 10.dp, end = 10.dp, bottom = 10.dp),
                        initialColor = RootS.oidColor,
                        controller = controller,
                        onColorChanged = { colorEnvelope: ColorEnvelope ->
                            currentColor = colorEnvelope.color // ARGB color value.
                            hexOfCurrentColor = currentColor.toHex()
                        }
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
//                    }
                }

//  ---font size
                stickyHeader {
                    DesignTitle(stringResource(R.string.font_size))
                }
                item {
//                    Card(){
                    SpacerS(20)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center

                    ) {
                        ButtonInDesign(
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
                                .padding(horizontal = 10.dp),
                            text = currentFontSize.toString(),
                            fontSize = 20.sp,
                            fontFamily = Font.jetBrainMonoMedium

                        )
                        ButtonInDesign(
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
//                    }
                }
            }
            SpacerS(100)
//            }


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
) {
    Row(
        modifier = Modifier
            .padding(bottom = 50.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        ButtonInDesign(
            ButtonType.MEDIUM,
            R.drawable.clear_icon,
            onClick = {
                scope.launch {
                    settingStoreManager.saveColor(root, RootS.oidColor.toArgb())
                    settingStoreManager.saveFontSize(root, RootS.oldFontSize)
                }
                navController.popBackStack()
            })

        ButtonInDesign(
            ButtonType.MEDIUM,
            R.drawable.ok_icon,
            onClick = {
                scope.launch {
                    settingStoreManager.saveColor(root, currentColor.toArgb())
                    settingStoreManager.saveFontSize(root, currentFontSize)
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
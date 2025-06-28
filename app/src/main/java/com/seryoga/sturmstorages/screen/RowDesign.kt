package com.seryoga.sturmstorages.screen

import SettingStoreManager
import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
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
import androidx.compose.ui.unit.DpOffset
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
import com.seryoga.sturmstorages.model.ButtonType
import com.seryoga.sturmstorages.model.DesignS
import com.seryoga.sturmstorages.model.DisplayType
import com.seryoga.sturmstorages.model.NavRoutes
import com.seryoga.sturmstorages.model.SettingData
import com.seryoga.sturmstorages.model.SettingDesign
import com.seryoga.sturmstorages.model.VisiblePicker
import com.seryoga.sturmstorages.ui.theme.Font
import com.seryoga.sturmstorages.util.Const.testItem
import com.seryoga.sturmstorages.util.ViewModelProduct
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RowDesign(
    navController: NavHostController,
    settings: SettingData,
    vmProduct: ViewModelProduct,
) {

    val scope = rememberCoroutineScope()
    val settingStoreManager = SettingStoreManager(LocalContext.current)

    var decorateItem by remember { mutableStateOf(DesignS.HIGHLIGHT_DESIGN) }
    var displayType by remember { mutableStateOf(DisplayType.ALL_IN_ROW) }
    var currentColor by remember {
        mutableStateOf(Color(settings.mapBand[DesignS.map[decorateItem]?.get(0)] as Int))
    }
    var currentBackgroundColor by remember {
        mutableStateOf(
            if (decorateItem == DesignS.HIGHLIGHT_DESIGN
                || decorateItem == DesignS.COLOR_OF_ROW_BACKGROUND_ID
            ) {

                Color(
                    settings.mapBand[DesignS.map[decorateItem]?.get(
                        3
                    )] as Int
                )
            } else {
                Color.Transparent
            }
        )
    }
    var currentFontSize by remember { mutableStateOf(0) }
    var currentFontFamily by remember { mutableStateOf(Font.JET_BRAIN) }
    var currentTextDecoration by remember { mutableStateOf(false) }

    val controller = rememberColorPickerController()
    var hexOfCurrentColor by remember { mutableStateOf(currentColor.toHex()) }
    var hexOfCurrentColorBackground by remember { mutableStateOf(currentColor.toHex()) }


    var expandedDecorChosen by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            ScreenTitleMain(
                content = {
                    ScreenTitleText(
                        stringResource(DesignS.titleAndIcons.getValue(decorateItem)[0]),
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
                                            isSelected = decorateItem == key,
                                            fontSize = 20,
                                            onClick = {
                                                decorateItem = key
                                                Log.i("MyLog", "title - ${decorateItem}");
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
//                    modifier = Modifier
//                        .padding(10.dp)
                ) {
                    DesignS.titleAndIcons.keys.toList().forEach { key ->
                        Box(
                            modifier = Modifier
                                .padding(10.dp)
                                .clickable {
                                    decorateItem = key
                                    Log.i("MyLog", "title - ${decorateItem}")
                                    expandedDecorChosen = false
                                }
//                                .border(
//                                    width = 2.dp,
//                                    shape = RoundedCornerShape(8.dp),
//                                    color = MaterialTheme.colorScheme.onPrimary
//                                )
                        ) {
                            IconInListClicked(
                                list = DesignS.titleAndIcons.getValue(key),
                                isSelected = decorateItem == key,
                                onClick = {
                                    decorateItem = key
                                    expandedDecorChosen = false
                                }
                            )
//                            Text(
//                                modifier = Modifier
//                                .padding(vertical = 10.dp, horizontal = 5.dp),
//                                fontFamily = Font.jetBrainMonoBold,
//                                text = stringResource(DesignS.title[key]?.get(0) )
//                            )
                        }


                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        )
        {

            key(decorateItem) {
                Log.i("MyLog", "decorateItem = ${decorateItem}");

            }
            when (decorateItem) {
                DesignS.PRODUCT_DESIGN,
                DesignS.PRICE_DESIGN,
                DesignS.QUANTITY_DESIGN,
//                DesignS.PROVIDER_SECOND_DESIGN,
                    -> {
                    currentColor = Color(settings.mapBand[DesignS.map[decorateItem]?.get(0)] as Int)
                    currentFontSize = settings.mapBand[DesignS.map[decorateItem]?.get(1)] as Int
                    currentFontFamily =
                        settings.mapBand[DesignS.map[decorateItem]?.get(2)] as String
                    with(VisiblePicker) {
                        COLOR_PICKER = true
                        FONT_SIZE_PICKER = true
                        FONT_FAMILY_PICKER = true
                        BACKGROUND_COLOR_PICKER = false
                        BACKGROUND_ACTIVE_COLOR_PICKER = false
                        TEXT_DECORATION_PICKER = false
                    }
                }

                DesignS.PROVIDER_DESIGN,
                    -> {
                    if (displayType == DisplayType.PROVIDER_HEADER) {
                        currentBackgroundColor =
                            Color(settings.mapBand[DesignS.map[decorateItem]?.get(3)] as Int)
                    }
                    currentColor = Color(settings.mapBand[DesignS.map[decorateItem]?.get(0)] as Int)
                    currentFontSize = settings.mapBand[DesignS.map[decorateItem]?.get(1)] as Int
                    currentFontFamily =
                        settings.mapBand[DesignS.map[decorateItem]?.get(2)] as String
                    with(VisiblePicker) {
                        COLOR_PICKER = true
                        FONT_SIZE_PICKER = true
                        FONT_FAMILY_PICKER = true
                        BACKGROUND_COLOR_PICKER = displayType == DisplayType.PROVIDER_HEADER
                        BACKGROUND_ACTIVE_COLOR_PICKER = false
                        TEXT_DECORATION_PICKER = false
                    }
                }
//                DesignS.COLOR_OF_PROVIDER_BACKGROUND_ID,
                DesignS.COLOR_OF_ROW_BACKGROUND_ID,
//                DesignS.COLOR_OF_ROW_BACKGROUND_ACTIVE_ID,
                    -> {
                    currentBackgroundColor =
                        Color(settings.mapBand[DesignS.map[decorateItem]?.get(3)] as Int)
                    with(VisiblePicker) {
                        COLOR_PICKER = false
                        FONT_SIZE_PICKER = false
                        FONT_FAMILY_PICKER = false
                        BACKGROUND_COLOR_PICKER = true
                        BACKGROUND_ACTIVE_COLOR_PICKER = true
                        TEXT_DECORATION_PICKER = false

                    }
                }

                DesignS.HIGHLIGHT_DESIGN -> {
                    currentColor = Color(settings.mapBand[DesignS.map[decorateItem]?.get(0)] as Int)
                    currentFontSize = settings.mapBand[DesignS.map[decorateItem]?.get(1)] as Int
                    currentFontFamily =
                        settings.mapBand[DesignS.map[decorateItem]?.get(2)] as String
                    currentBackgroundColor =
                        Color(settings.mapBand[DesignS.map[decorateItem]?.get(3)] as Int)
                    currentTextDecoration =
                        settings.mapBand[DesignS.map[decorateItem]?.get(4)] as Boolean
                    with(VisiblePicker) {
                        COLOR_PICKER = true
                        FONT_SIZE_PICKER = true
                        FONT_FAMILY_PICKER = true
                        BACKGROUND_COLOR_PICKER = true
                        TEXT_DECORATION_PICKER = true
                    }
                }

            }
            Box(
                modifier = Modifier
                    .animateContentSize()
            ) {

                when (displayType) {
                    DisplayType.ALL_IN_ROW -> {
                        AllInRow(
                            settings,
                            listOf(testItem),
                            settingDesign = SettingDesign(
                                name = decorateItem,
                                color = currentColor.toArgb(),
                                size = currentFontSize,
                                font = Font.mapFontsFamily[currentFontFamily],
                                backgroundColor = currentBackgroundColor.toArgb(),
                                decoration = currentTextDecoration,
                            ),
                            vmProduct
                        )
                    }

                    DisplayType.ALL_IN_ROW_AT_CELL -> {
                        AllInRowAtCell(
                            settings,
                            listOf(testItem),
                            settingDesign = SettingDesign(
                                name = decorateItem,
                                color = currentColor.toArgb(),
                                size = currentFontSize,
                                font = Font.mapFontsFamily[currentFontFamily],
                                backgroundColor = currentBackgroundColor.toArgb(),
                                decoration = currentTextDecoration,
                            ),
                            vmProduct
                        )
                    }

                    DisplayType.PROVIDER_HEADER -> {
                        ProviderHeader(
                            settings,
                            listOf(testItem).groupBy { it.provider },
                            settingDesign = SettingDesign(
                                name = decorateItem,
                                color = currentColor.toArgb(),
                                size = currentFontSize,
                                font = Font.mapFontsFamily[currentFontFamily],
                                backgroundColor = currentBackgroundColor.toArgb(),
                                decoration = currentTextDecoration,
                            ),
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
            key(decorateItem, displayType) {

//  ---  --- 
                LazyColumn {
//                LaunchedEffect(decorateItem) {
//  ___ Color picker___

                    if (VisiblePicker.COLOR_PICKER) {
//                        item{
//
//                            this@LazyColumn.ColorPicker(
//                                titleResource = R.string.color,
//                                currentColor,
//                                controller,
//                                hexOfCurrentColor,
//                                onColorChange = { colorEnvelope ->
//                                    currentColor = colorEnvelope.color // ARGB color value.
//                                    scope.launch {
//                                        settingStoreManager.saveColor(
//                                            decorateItem,
//                                            currentColor.toArgb()
//                                        )
//                                    }
//                                }
//                            )
//                        }
                        stickyHeader {
                            DesignTitle(stringResource(R.string.color))
                        }
                        item {

                            ColorPickerBlock(
//                                title = stringResource(R.string.color),
                                initialColor = currentColor,
                                controller = controller,
                                onColorChange = { color ->
                                    currentColor = color
                                    hexOfCurrentColor = color.toHex()
                                    scope.launch {
                                        settingStoreManager.saveColor(
                                            decorateItem,
                                            color.toArgb()
                                        )
                                    }
                                }
                            )
                        }

//  ___ Background color picker ___
                        if (VisiblePicker.BACKGROUND_COLOR_PICKER) {
                            Log.i("MyLog", "background");
                            stickyHeader {
                                DesignTitle(stringResource(R.string.backgroundColor))
                            }
                            item {

                                ColorPickerBlock(
                                    initialColor = currentBackgroundColor,
                                    controller = rememberColorPickerController(),
                                    onColorChange = { color ->
                                        currentBackgroundColor = color
                                        hexOfCurrentColorBackground = color.toHex()
                                        scope.launch {
                                            settingStoreManager.saveBackgroundColor(
                                                decorateItem,
                                                color.toArgb()
                                            )
                                        }
                                    }
                                )
                            }
                        }
                    }
////  ___ Background active row color picker ___
//                        if (VisiblePicker.) {
//                            stickyHeader {
//                                DesignTitle(stringResource(R.string.backgroundColor))
//                            }
//                            item {
//
//                                ColorPickerBlock(
//                                    initialColor = currentBackgroundColor,
//                                    controller = rememberColorPickerController(),
//                                    onColorChange = { color ->
//                                        currentBackgroundColor = color
//                                        hexOfCurrentColorBackground = color.toHex()
//                                        scope.launch {
//                                            settingStoreManager.saveBackgroundColor(
//                                                decorateItem,
//                                                color.toArgb()
//                                            )
//                                        }
//                                    }
//                                )
//
//                        }
//
//                    }
//  ___ Font size picker ___
                    if (VisiblePicker.FONT_SIZE_PICKER) {
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
                                        if (currentFontSize < 20) currentFontSize =
                                            ++currentFontSize
                                        scope.launch {
                                            settingStoreManager.saveFontSize(
                                                decorateItem,
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
                                                decorateItem,
                                                currentFontSize
                                            )
                                        }
                                    }
                                )
                            }
                            SpacerS(20)
                        }
                    }

//  ___ Font family picker ___
                    if (VisiblePicker.FONT_FAMILY_PICKER) {
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
                                ButtonTextS(
                                    ButtonType.SMALL,
                                    stringResource(R.string.roboto),
                                    fontFamily = Font.robotoMedium,
                                    color = if (currentFontFamily == Font.ROBOTO) {
                                        MaterialTheme.colorScheme.onTertiary
                                    } else MaterialTheme.colorScheme.onPrimary,
                                    onClick = {
                                        currentFontFamily = Font.ROBOTO
                                        scope.launch {
                                            settingStoreManager.saveFontFamily(
                                                decorateItem,
                                                currentFontFamily
                                            )
                                        }
                                    }
                                )
                                SpacerS(20)
                                ButtonTextS(
                                    ButtonType.SMALL,
                                    stringResource(R.string.jet_brain),
                                    fontFamily = Font.robotoMedium,
                                    color = if (currentFontFamily == Font.JET_BRAIN) {
                                        MaterialTheme.colorScheme.onTertiary
                                    } else MaterialTheme.colorScheme.onPrimary,
                                    onClick = {
                                        currentFontFamily = Font.JET_BRAIN
                                        scope.launch {
                                            settingStoreManager.saveFontFamily(
                                                decorateItem,
                                                currentFontFamily
                                            )
                                        }
                                    }
                                )
                                SpacerS(20)
                                ButtonTextS(
                                    ButtonType.SMALL,
                                    stringResource(R.string.comic_relief),
                                    fontFamily = Font.comicReliefRegular,
                                    color = if (currentFontFamily == Font.COMIC_RELIEF) {
                                        MaterialTheme.colorScheme.onTertiary
                                    } else MaterialTheme.colorScheme.onPrimary,
                                    onClick = {
                                        currentFontFamily = Font.COMIC_RELIEF
                                        scope.launch {
                                            settingStoreManager.saveFontFamily(
                                                decorateItem,
                                                currentFontFamily
                                            )
                                        }
                                    }
                                )
                                SpacerS(20)
                                ButtonTextS(
                                    ButtonType.SMALL,
                                    stringResource(R.string.sans_narrow),
                                    fontFamily = Font.sansNarrowRegular,
                                    color = if (currentFontFamily == Font.SANS_NARROW) {
                                        MaterialTheme.colorScheme.onTertiary
                                    } else MaterialTheme.colorScheme.onPrimary,
                                    onClick = {
                                        currentFontFamily = Font.SANS_NARROW
                                        scope.launch {
                                            settingStoreManager.saveFontFamily(
                                                decorateItem,
                                                currentFontFamily
                                            )
                                        }
                                    }
                                )
                                SpacerS(20)
                            }
                        }
                    }

//
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


//SpacerS(20)
////                        key(decorateItem) {
//                            Box(
//                                modifier = Modifier
//                                    .fillMaxWidth(),
//                                contentAlignment = Alignment.Center
//                            ) {
//
//                                HsvColorPicker(
//                                    modifier = Modifier
//                                        .width(200.dp)
//                                        .height(200.dp)
//                                        .padding(
//                                            top = 0.dp,
//                                            start = 10.dp,
//                                            end = 10.dp,
//                                            bottom = 10.dp
//                                        ),
//                                    onColorChanged = { colorEnvelope: ColorEnvelope ->
//
//                                        currentColor = colorEnvelope.color // ARGB color value.
//                                        scope.launch {
//                                            settingStoreManager.saveColor(
//                                                decorateItem,
//                                                currentColor.toArgb()
//                                            )
//
//                                        }
//                                        hexOfCurrentColor = currentColor.toHex()
//                                    },
//                                    controller = controller,
//                                    initialColor = currentColor
//                                )
//
//                            }
//                            AlphaSlider(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(vertical = 10.dp, horizontal = 20.dp)
//                                    .height(35.dp)
//                                    .border(
//                                        1.dp,
//                                        MaterialTheme.colorScheme.onPrimary,
//                                        RoundedCornerShape(6.dp)
//                                    ),
//                                initialColor = currentColor,
//                                controller = controller,
//
//                                )
//                            BrightnessSlider(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(vertical = 10.dp, horizontal = 20.dp)
//                                    .height(35.dp)
//                                    .border(
//                                        1.dp,
//                                        MaterialTheme.colorScheme.onPrimary,
//                                        RoundedCornerShape(6.dp)
//                                    ),
//                                initialColor = currentColor,
//                                controller = controller,
//                            )
//
//                            Row(
//                                modifier = Modifier.fillMaxWidth(),
//                                horizontalArrangement = Arrangement.Center,
//                                verticalAlignment = Alignment.CenterVertically
//                            ) {
//                                AlphaTile(
//                                    modifier = Modifier
//                                        .width(100.dp)
//                                        .padding(top = 10.dp)
//                                        .height(40.dp)
//                                        .clip(RoundedCornerShape(6.dp))
//                                        .border(
//                                            1.dp,
//                                            invertColor(currentColor),
//                                            RoundedCornerShape(6.dp)
//                                        ),
//                                    controller = controller,
//                                )
//                                Text(
//                                    modifier = Modifier
//                                        .fillMaxHeight()
//                                        .padding(top = 10.dp, start = 20.dp),
////                                    textAlign = TextAlign.Center,
//                                    text = hexOfCurrentColor,
//                                    color = MaterialTheme.colorScheme.onPrimary
//                                )
//                            }
//                            SpacerS(20)
//
package com.seryoga.sturmstorages.screen

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
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
//    val context = LocalContext.current
    val settingStoreManager = SettingStoreManager(LocalContext.current)
    var decorateItem by remember { mutableStateOf(DesignS.PRODUCT_DESIGN) }
    var displayType by remember { mutableStateOf(DisplayType.ALL_IN_ROW) }

    var currentColor =
        Color(settingStoreManager.getColorOfProduct().collectAsState(Color.Red.toArgb()).value)
    Log.i("MyLog", "RowDesign in head ${currentColor.toHex()}");
    var currentFontSize = settingStoreManager.getFontSizeProduct().collectAsState(0).value
    var currentBackgroundColor by remember {
        mutableStateOf(
            if (decorateItem == DesignS.HIGHLIGHT_DESIGN
                || decorateItem == DesignS.COLOR_OF_ROW_BACKGROUND_ID
            ) {
                Color(settings.mapBand[DesignS.map[decorateItem]?.get(3)] as Int)
            } else {
                Color.Transparent
            }
        )
    }
    var currentDiffProviderColor by remember {
        mutableStateOf(Color(settings.mapBand[DesignS.map[DesignS.PROVIDER_DESIGN]?.get(4)] as Int))
    }
    var currentBackgroundActiveColor by remember {
        mutableStateOf(Color(settings.mapBand[DesignS.map[DesignS.BACKGROUND_DESIGN]?.get(5)] as Int))
    }

//    var currentFontSize by remember { mutableStateOf(0) }
    var currentFontFamily by remember { mutableStateOf(Font.JET_BRAIN) }
    val currentTextDecorationOfHighlight by settingStoreManager.getTextDecorationOfHighlight()
        .collectAsState("000")

    val controller = rememberColorPickerController()
//    var hexOfCurrentColor by remember { mutableStateOf(currentColor.toHex()) }
    val hexOfCurrentColor by remember { derivedStateOf { currentColor.toHex() } }
    var hexOfCurrentColorBackground by remember { mutableStateOf(currentBackgroundColor.toHex()) }
    var hexOfCurrentColorDifferentProvider by remember { mutableStateOf(currentDiffProviderColor.toHex()) }
    var hexOfCurrentColorBackgroundActive by remember { mutableStateOf(currentBackgroundActiveColor.toHex()) }
    var isDifferentProvider by remember { mutableStateOf(false) }

    var expandedDecorChosen by remember { mutableStateOf(false) }

//    key(currentTextDecorationOfHighlight){
//        Log.i("MyLog", "currentTextDecorationOfHighlight: ${currentTextDecorationOfHighlight}");
//    }
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
                        ) {
                            IconInListClicked(
                                list = DesignS.titleAndIcons.getValue(key),
                                isSelected = decorateItem == key,
                                onClick = {
                                    decorateItem = key
                                    expandedDecorChosen = false
                                }
                            )
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
//            currentColor = settingStoreManager.getColorOfElement(decorateItem).collectAsState("0")
            when (decorateItem) {
                DesignS.PRODUCT_DESIGN -> {
                    currentColor =
                        Color(settingStoreManager.getColorOfProduct().collectAsState(0).value)
                    currentFontSize = settings.mapBand[DesignS.map[decorateItem]?.get(1)] as Int
                    currentFontFamily =
                        settings.mapBand[DesignS.map[decorateItem]?.get(2)] as String
                }

                DesignS.PRICE_DESIGN,
                DesignS.QUANTITY_DESIGN,
                    -> {
                    1
                    currentColor = Color(settings.mapBand[DesignS.map[decorateItem]?.get(0)] as Int)
                    currentFontSize = settings.mapBand[DesignS.map[decorateItem]?.get(1)] as Int
                    currentFontFamily =
                        settings.mapBand[DesignS.map[decorateItem]?.get(2)] as String
                }

                in listOf(
                    DesignS.PRODUCT_DESIGN,
                    DesignS.PRICE_DESIGN,
                    DesignS.QUANTITY_DESIGN
                ),
                    -> {
                    Log.i("MyLog", "list - DONE");
                    with(VisiblePicker) {
                        COLOR_PICKER = true
                        FONT_SIZE_PICKER = true
                        FONT_FAMILY_PICKER = true
                        BACKGROUND_COLOR_PICKER = false
                        DIFFERENT_PROVIDER_COLOR_PICKER = false
                        BACKGROUND_ACTIVE_COLOR_PICKER = false
                        TEXT_DECORATION_PICKER = false
                    }
                }

                DesignS.PROVIDER_DESIGN,
                    -> {
                    Log.i("MyLog", "Design -> Provider ");
                    if (displayType == DisplayType.PROVIDER_HEADER) {
                        currentBackgroundColor =
                            Color(settings.mapBand[DesignS.map[decorateItem]?.get(4)] as Int)
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
                        DIFFERENT_PROVIDER_COLOR_PICKER = true
                        BACKGROUND_ACTIVE_COLOR_PICKER = false
                        TEXT_DECORATION_PICKER = false
                    }
                }

                DesignS.BACKGROUND_DESIGN,
                    -> {
                    Log.i("MyLog", "Design -> Background ");
                    currentBackgroundColor =
                        Color(settings.mapBand[DesignS.map[decorateItem]?.get(3)] as Int)
                    with(VisiblePicker) {
                        COLOR_PICKER = false
                        FONT_SIZE_PICKER = false
                        FONT_FAMILY_PICKER = false
                        BACKGROUND_COLOR_PICKER = true
                        DIFFERENT_PROVIDER_COLOR_PICKER = false
                        BACKGROUND_ACTIVE_COLOR_PICKER = true
                        TEXT_DECORATION_PICKER = false

                    }
                }

                DesignS.HIGHLIGHT_DESIGN -> {
                    Log.i("MyLog", "Design -> Highlight ");
                    currentColor = Color(settings.mapBand[DesignS.map[decorateItem]?.get(0)] as Int)
                    currentFontSize = settings.mapBand[DesignS.map[decorateItem]?.get(1)] as Int
                    currentFontFamily =
                        settings.mapBand[DesignS.map[decorateItem]?.get(2)] as String
                    currentBackgroundColor =
                        Color(settings.mapBand[DesignS.map[decorateItem]?.get(3)] as Int)
                    with(VisiblePicker) {
                        COLOR_PICKER = true
                        FONT_SIZE_PICKER = true
                        FONT_FAMILY_PICKER = true
                        BACKGROUND_COLOR_PICKER = true
                        DIFFERENT_PROVIDER_COLOR_PICKER = false
                        BACKGROUND_ACTIVE_COLOR_PICKER = false
                        TEXT_DECORATION_PICKER = true
                    }
                }

            }



            when (decorateItem) {
                DesignS.PRODUCT_DESIGN,
                DesignS.PRICE_DESIGN,
                DesignS.QUANTITY_DESIGN,
                    -> {
                    with(VisiblePicker) {
                        COLOR_PICKER = true
                        FONT_SIZE_PICKER = true
                        FONT_FAMILY_PICKER = true
                        BACKGROUND_COLOR_PICKER = false
                        DIFFERENT_PROVIDER_COLOR_PICKER = false
                        BACKGROUND_ACTIVE_COLOR_PICKER = false
                        TEXT_DECORATION_PICKER = false
                    }
                }
            }

            Box(
                modifier = Modifier
                    .animateContentSize()
            ) {

                when (displayType) {
                    DisplayType.ALL_IN_ROW -> {
                        Log.i("MyLog", "currCol = ${currentColor.toHex()}");
                        AllInRow(
                            settings,
                            listOf(testItem),
                            settingDesign = SettingDesign(
                                name = decorateItem,
                                color = if (decorateItem == DesignS.PROVIDER_DESIGN && isDifferentProvider) {
                                    currentDiffProviderColor.toArgb()
                                } else currentColor.toArgb(),
                                size = currentFontSize,
                                font = Font.mapFontsFamily[currentFontFamily],
                                backgroundColor = currentBackgroundColor.toArgb(),
//                                decoration = currentTextDecorationOfHighlight,
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
//                                decoration = currentTextDecorationOfHighlight,
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
//                                decoration = currentTextDecorationOfHighlight,
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
                        stickyHeader {
                            DesignTitle(stringResource(R.string.color))
                        }
                        item {
                            Log.i("MyLog", "Before ColorPicker ${currentColor}");
                            ColorPickerBlock(
                                initialColor = currentColor,
                                controller = controller,
                                onColorChange = { color ->
                                    Log.i("MyLog", "*** onColorChange ${color.toArgb()}");
                                    Log.i("MyLog", "*** onColorChange (current color) ${currentColor.toHex()}");
                                    currentColor = color
//                                    hexOfCurrentColor = color.toHex()
                                    scope.launch {
                                        settingStoreManager.saveColor(
                                            decorateItem,
                                            color.toArgb()
                                        )
                                    }
                                    if (isDifferentProvider) isDifferentProvider = false
                                },
                            )
                        }
                    }
//  ___ Background color picker ___
                    if (VisiblePicker.BACKGROUND_COLOR_PICKER) {
//                        Log.i("MyLog", "BACKGROUND SHOW");
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
//                                        settingStoreManager.saveBackgroundColor(
//                                            decorateItem,
//                                            color.toArgb()
//                                        )
                                    }
                                },
//                                isTransparentDisplay = decorateItem == DesignS.HIGHLIGHT_DESIGN
                            )
                        }
                    }

//  ___ Background different provider color picker ___
                    if (VisiblePicker.DIFFERENT_PROVIDER_COLOR_PICKER) {
                        stickyHeader {
                            DesignTitle(stringResource(R.string.different_provider_color))
                        }
                        item {
                            ColorPickerBlock(
                                initialColor = currentDiffProviderColor,
                                controller = rememberColorPickerController(),
                                onColorChange = { color ->
                                    currentDiffProviderColor = color
                                    hexOfCurrentColorDifferentProvider = color.toHex()
                                    scope.launch {
                                        settingStoreManager.saveDifferentProviderColor(color.toArgb())
                                    }
                                    if (!isDifferentProvider) isDifferentProvider = true
                                }
                            )

                        }

                    }
//  ___ Background active row color picker ___
                    if (VisiblePicker.BACKGROUND_ACTIVE_COLOR_PICKER) {
                        stickyHeader {
                            DesignTitle(stringResource(R.string.background_active_color))
                        }
                        item {

                            ColorPickerBlock(
                                initialColor = currentBackgroundActiveColor,
                                controller = rememberColorPickerController(),
                                onColorChange = { color ->
                                    currentBackgroundActiveColor = color
                                    hexOfCurrentColorBackgroundActive = color.toHex()
                                    scope.launch {
                                        settingStoreManager.saveBackgroundActiveColor(color.toArgb())
                                    }
                                }
                            )

                        }

                    }
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
                                        Log.i("MyLog", "*********Minus click**********");
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

//  ___ Text decorations picker ___
                    Log.i("MyLog", "DECORATION SHOW");
                    if (VisiblePicker.TEXT_DECORATION_PICKER) {
                        stickyHeader {
                            DesignTitle(stringResource(R.string.text_decoration))
                        }
                        item {
                            SpacerS(20)
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceAround
                            ) {

                                ButtonWithIcon(
                                    ButtonType.SMALL,
                                    R.drawable.bold_icon,
                                    tint = if (currentTextDecorationOfHighlight.toString()[0] == '1') {
                                        MaterialTheme.colorScheme.onTertiary
                                    } else MaterialTheme.colorScheme.onPrimary,
                                    onClick = {
                                        val firstChar =
                                            if (currentTextDecorationOfHighlight[0] == '0') '1' else '0'
                                        val secondChar =
                                            currentTextDecorationOfHighlight[1].toString()
                                        val thirdChar =
                                            currentTextDecorationOfHighlight[2].toString()
                                        val newCurrentTextDecorationOfHighlight =
                                            firstChar + secondChar + thirdChar
                                        scope.launch {
//                                            settingStoreManager.saveToDataStore(newCurrentTextDecorationOfHighlight, DesignS.TEXT_DECORATION_OF_HIGHLIGHT_ID, String::class.java)
                                            settingStoreManager.saveTextDecorationOfHighlight(
                                                newCurrentTextDecorationOfHighlight
                                            )
                                        }
                                    },

                                    )
                                ButtonWithIcon(
                                    ButtonType.SMALL,
                                    R.drawable.italic_icon,
                                    tint = if (currentTextDecorationOfHighlight[1] == '1') {
                                        MaterialTheme.colorScheme.onTertiary
                                    } else MaterialTheme.colorScheme.onPrimary,
                                    onClick = {
//                                        Log.i("MyLog", "************click**********");
                                        val firstChar =
                                            currentTextDecorationOfHighlight[0].toString()
                                        val secondChar =
                                            if (currentTextDecorationOfHighlight.toString()[1] == '0') '1' else '0'
                                        val thirdChar =
                                            currentTextDecorationOfHighlight[2].toString()
                                        val newCurrentTextDecorationOfHighlight =
                                            firstChar + secondChar + thirdChar
                                        scope.launch {
//                                            settingStoreManager.saveToDataStore(newCurrentTextDecorationOfHighlight, DesignS.TEXT_DECORATION_OF_HIGHLIGHT_ID, String::class.java)
                                            settingStoreManager.saveTextDecorationOfHighlight(
                                                newCurrentTextDecorationOfHighlight
                                            )
                                        }
                                    },

                                    )
                                ButtonWithIcon(
                                    ButtonType.SMALL,
                                    R.drawable.underline_icon,
                                    tint = if (currentTextDecorationOfHighlight[2] == '1') {
                                        MaterialTheme.colorScheme.onTertiary
                                    } else MaterialTheme.colorScheme.onPrimary,
                                    onClick = {
                                        val firstChar =
                                            currentTextDecorationOfHighlight[0].toString()
                                        val secondChar =
                                            currentTextDecorationOfHighlight[1].toString()
                                        val thirdChar =
                                            if (currentTextDecorationOfHighlight[2] == '0') '1' else '0'
                                        val newCurrentTextDecorationOfHighlight =
                                            firstChar + secondChar + thirdChar
                                        scope.launch {
//                                            settingStoreManager.saveToDataStore(newCurrentTextDecorationOfHighlight, DesignS.TEXT_DECORATION_OF_HIGHLIGHT_ID, String::class.java)
                                            settingStoreManager.saveTextDecorationOfHighlight(
                                                newCurrentTextDecorationOfHighlight
                                            )
                                        }
                                    },

                                    )
//


//                                IconButton(
//                                    onClick = {
//                                        var newChar = if(currentTextDecoration[0] == '0') '1' else '0'
//                                        currentTextDecoration = newChar + currentTextDecoration.substring(1)
//                                    }
//                                ) {
//                                    Icon(
//                                        painter = painterResource(R.drawable.bold_icon),
//                                        contentDescription = stringResource(R.string.setting_display_type_all_in_row),
//                                        tint = if (currentTextDecoration[0] == '1') {
//                                            MaterialTheme.colorScheme.onTertiary
//                                        } else MaterialTheme.colorScheme.onPrimary
//                                    )
//                                }
//                                IconButton(
//                                    onClick = {
//                                            var newChar = if(currentTextDecoration[1] == '0') '1' else '0'
//                                            currentTextDecoration =
//                                            currentTextDecoration.substring (0, 1) + newChar + currentTextDecoration.substring(2)
//                                    }
//                                ) {
//                                    Icon(
//                                        painter = painterResource(R.drawable.italic_icon),
//                                        contentDescription = stringResource(R.string.setting_display_type_all_in_row),
//                                        tint = if (currentTextDecoration[1] == '1') {
//                                            MaterialTheme.colorScheme.onTertiary
//                                        } else MaterialTheme.colorScheme.onPrimary
//                                    )
//                                }
//                                IconButton(
//                                    onClick = {
//                                        var newChar = if(currentTextDecoration[2] == '0') '1' else '0'
//                                        currentTextDecoration =
//                                            currentTextDecoration.substring (0, 2) + newChar
//                                    }
//                                ) {
//                                    Icon(
//                                        painter = painterResource(R.drawable.underline_icon),
//                                        contentDescription = stringResource(R.string.setting_display_type_all_in_row),
//                                        tint = if (currentTextDecoration[2] == '1') {
//                                            MaterialTheme.colorScheme.onTertiary
//                                        } else MaterialTheme.colorScheme.onPrimary
//                                    )
//                                }
                            }
                        }
                    }
//  ___ Reset to default ___
                    stickyHeader {
                        DesignTitle(stringResource(R.string.reset_to_default))
                    }
                    item {
                        SpacerS(20)
                        IconButton(
                            onClick = {
//                                resetT(decorateItem)
                                DesignS.map.getValue(decorateItem).forEachIndexed { index, item ->
                                    if (item.isNotEmpty()) {

                                        Log.i("MyLog", "${index} item -> ${item}")
                                        scope.launch {
                                            when (index) {
                                                0 -> {
                                                    settingStoreManager.saveColor(
                                                        decorateItem,
                                                        DesignS.default.getValue(item) as Int
                                                    )
                                                    currentColor =
                                                        Color(DesignS.default.getValue(item) as Int)
                                                }

                                                1 -> {
                                                    settingStoreManager.saveFontSize(
                                                        decorateItem,
                                                        DesignS.default.getValue(item) as Int
                                                    )
                                                }

                                                2 -> {
                                                    settingStoreManager.saveFontFamily(
                                                        decorateItem,
                                                        DesignS.default.getValue(item) as String
                                                    )
                                                }

                                                3 -> {
                                                    settingStoreManager.saveBackgroundColor(
                                                        decorateItem,
                                                        DesignS.default.getValue(item) as Int
                                                    )
                                                }
//                                                4 -> settingStoreManager.s
                                            }
                                        }
//                                        refresh = !refresh

                                    }

                                }
                            }
                        ) {
                            Text(
                                text = stringResource(R.string.reset_to_default)
                            )
                        }
                    }
                }
            }
        }

//        fun resetToDefault(decorateItem: String) {
//            DesignS.map.getValue(decorateItem).forEach { list ->
//                when (decorateItem) {
//                    DesignS.PRODUCT_DESIGN,
//                    DesignS.PRICE_DESIGN,
//                    DesignS.QUANTITY_DESIGN,
//                        -> {
//                        currentColor =
//                            Color(DesignS.default.getValue(DesignS.map.getValue(decorateItem)[0]) as Int)
//                        currentFontSize =
//                            DesignS.default.getValue(DesignS.map.getValue(decorateItem)[1]) as Int
//                        currentFontFamily =
//                            DesignS.default.getValue(DesignS.map.getValue(decorateItem)[2]) as String
//                    }
//
//                    DesignS.PROVIDER_DESIGN,
//                        -> {
//                        currentColor =
//                            Color(DesignS.default.getValue(DesignS.COLOR_OF_PROVIDER_ID) as Int)
//                        currentFontSize =
//                            DesignS.default.getValue(DesignS.FONT_SIZE_OF_PROVIDER_ID) as Int
//                        currentFontFamily =
//                            DesignS.default.getValue(DesignS.FONT_FAMILY_OF_PROVIDER_ID) as String
//                        currentDiffProviderColor =
//                            Color(DesignS.default.getValue(DesignS.COLOR_OF_PROVIDER_SECOND_ID) as Int)
//                    }
//
//                    DesignS.BACKGROUND_DESIGN,
//                        -> {
//                        currentBackgroundColor =
//                            Color(DesignS.default.getValue(DesignS.COLOR_OF_ROW_BACKGROUND_ID) as Int)
//                        currentBackgroundActiveColor =
//                            Color(DesignS.default.getValue(DesignS.COLOR_OF_ROW_BACKGROUND_ACTIVE_ID) as Int)
//
//                    }
//
//                    DesignS.HIGHLIGHT_DESIGN -> {
//                        currentColor =
//                            Color(DesignS.default.getValue(DesignS.COLOR_OF_HIGHLIGHT_ID) as Int)
//                        currentFontSize =
//                            DesignS.default.getValue(DesignS.FONT_SIZE_OF_HIGHLIGHT_ID) as Int
//                        currentFontFamily =
//                            DesignS.default.getValue(DesignS.FONT_FAMILY_OF_HIGHLIGHT_ID) as String
//                        currentBackgroundColor =
//                            Color(DesignS.default.getValue(DesignS.COLOR_OF_HIGHLIGHT_BACKGROUND_ID) as Int)
////                    todo Text decoration
//
//                    }
//
//                }
//            }
//        }
    }
}

//private fun flipBit(c: Char): Char {
//    return when (c) {
//        '0' -> '1'
//        '1' -> '0'
//        else -> c
//    }
//}

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
package com.seryoga.sturmstorages.model

import SettingStoreManager
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.seryoga.sturmstorages.R
import com.seryoga.sturmstorages.screen.AllInRow
import com.seryoga.sturmstorages.screen.AllInRowAtCell
import com.seryoga.sturmstorages.screen.ButtonTextS
import com.seryoga.sturmstorages.screen.ButtonWithIcon
import com.seryoga.sturmstorages.screen.ColorPickerBlock
import com.seryoga.sturmstorages.screen.DesignTitle
import com.seryoga.sturmstorages.screen.IconInListClicked
import com.seryoga.sturmstorages.screen.ProviderHeader
import com.seryoga.sturmstorages.screen.ScreenTitleMain
import com.seryoga.sturmstorages.screen.ScreenTitleText
import com.seryoga.sturmstorages.screen.ScreenTitleTextClicked
import com.seryoga.sturmstorages.screen.SpacerS
import com.seryoga.sturmstorages.ui.theme.Font
import com.seryoga.sturmstorages.util.Const.testItem
import com.seryoga.sturmstorages.util.ViewModelProduct
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun RowDisplaySetting(
    navController: NavHostController,
    vmProduct: ViewModelProduct,
) {

    val settingStoreManager = SettingStoreManager(LocalContext.current)
    val scope = rememberCoroutineScope()
    var expandedDecorChosen by remember { mutableStateOf(false) }
    val itemToDesign = vmProduct.itemToDesign.collectAsState()
    var displayType by remember { mutableStateOf(DisplayType.ALL_IN_ROW) }
    var visiblePickers by remember { mutableStateOf(VisiblePicker()) }
    val allSettings by vmProduct.allSettings.collectAsState()

    var currentColor by remember { mutableStateOf(Color(allSettings.colorOfProduct)) }
    var currentColorOfProviderSecond by remember { mutableStateOf(Color(allSettings.colorOfProviderSecond)) }
    var currentColorOfRowBackground by remember { mutableStateOf(Color(allSettings.colorOfRowBackground)) }
    var currentColorOfRowBackgroundActive by remember { mutableStateOf(Color(allSettings.colorOfRowBackgroundActive)) }
    var currentColorOfProviderBackground by remember { mutableStateOf(Color(allSettings.colorOfRowBackgroundActive)) }
    var currentColorOfHighlightBackground by remember { mutableStateOf(Color(allSettings.colorOfHighlightBackground)) }
    var controller = rememberColorPickerController()
    var controllerProviderSecond = rememberColorPickerController()
    var controllerRowBackground = rememberColorPickerController()
    var controllerRowBackgroundActive = rememberColorPickerController()
    var controllerProviderBackground = rememberColorPickerController()
    var controllerHighlightBackground = rememberColorPickerController()
    var currentFontSize by remember { mutableStateOf(allSettings.fontSizeOfProduct) }
    var currentFontFamily by remember { mutableStateOf(allSettings.fontFamilyOfProduct) }
    var currentDecoration by remember { mutableStateOf(allSettings.decorationOfProduct) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    ScreenTitleText(
                        stringResource(DesignS.titleAndIcons.getValue(itemToDesign.value)[0]),
                    )
//                    Box(
//                        modifier = Modifier
////                            .align(Alignment.CenterEnd)
//                            .padding(end = 10.dp),
//                    ) {
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                            navController.navigate(NavRoutes.Setting.route) {
                                launchSingleTop = true
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.back_icon),
                            contentDescription = stringResource(R.string.back_button),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                actions = {
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
                                                expandedDecorChosen = false
                                            }
                                        )
                                    },
                                    onClick = {
                                    }
                                )
                            }
                        }
//                    }
                }
            )
//            ScreenTitleMain(
//                content = {
//                    ScreenTitleText(
//                        stringResource(DesignS.titleAndIcons.getValue(itemToDesign.value)[0]),
//                    )
//                    Box(
//                        modifier = Modifier
//                            .align(Alignment.CenterEnd)
//                            .padding(end = 10.dp),
//                    ) {
//
//                        IconButton(
//                            onClick = {
//                                expandedDecorChosen = !expandedDecorChosen
//                            }
//                        ) {
//                            Icon(
//                                painter = when (expandedDecorChosen) {
//                                    false -> painterResource(R.drawable.arrow_down)
//                                    true -> painterResource(R.drawable.arrow_up)
//                                },
//                                tint = MaterialTheme.colorScheme.onPrimary,
//                                contentDescription = stringResource(R.string.expandable_button)
//                            )
//                        }
//
//                        DropdownMenu(
//                            modifier = Modifier,
//                            offset = DpOffset(x = 0.dp, y = 10.dp),
//                            expanded = expandedDecorChosen,
//                            onDismissRequest = { expandedDecorChosen = false }
//                        ) {
//                            DesignS.titleAndIcons.forEach { (key, list) ->
//                                DropdownMenuItem(
//
//                                    text = {
//                                        ScreenTitleTextClicked(
//                                            list = list,
//                                            isSelected = itemToDesign.value == key,
//                                            fontSize = 20,
//                                            onClick = {
//                                                vmProduct.setItemToDesign(key)
//                                                expandedDecorChosen = false
//                                            }
//                                        )
//                                    },
//                                    onClick = {
//                                    }
//                                )
//                            }
//                        }
//                    }
//                },

//            )
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
                key(allSettings) {

                    when (displayType) {
                        DisplayType.ALL_IN_ROW -> {
                            AllInRow(
                                listOf(testItem),
                                vmProduct
                            )
                        }

                        DisplayType.ALL_IN_ROW_AT_CELL -> {
                            AllInRowAtCell(
                                listOf(testItem),
                                vmProduct
                            )
                        }

                        DisplayType.PROVIDER_HEADER -> {
                            ProviderHeader(
                                listOf(testItem).groupBy { it.provider },
                                vmProduct
                            )
                        }
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

            when (itemToDesign.value) {
                DesignS.PRODUCT_DESIGN -> {
                    currentColor = Color(allSettings.colorOfProduct)
                    currentFontSize = allSettings.fontSizeOfProduct
                    currentFontFamily = allSettings.fontFamilyOfProduct
                    currentDecoration = allSettings.decorationOfProduct
                    currentColorOfRowBackground = Color(allSettings.colorOfRowBackground)
                    currentColorOfRowBackgroundActive = Color(allSettings.colorOfRowBackgroundActive)
                    currentColorOfProviderSecond = Color(allSettings.colorOfProviderSecond)
                    currentColorOfHighlightBackground = Color(allSettings.colorOfHighlightBackground)
                    visiblePickers = VisiblePicker(
                        colorPicker = true,
                        fontSizePicker = true,
                        fontFamilyPicker = true,
                        decorationPicker = true,
                        colorOfProviderSecondPicker = false,
                        colorOfRowBackgroundPicker = false,
                        colorOfRowBackgroundActivePicker = false,
                        colorOfProviderBackgroundPicker = false,
                        colorOfHighlightBackgroundPicker = false,
                    )
                }

                DesignS.PRICE_DESIGN -> {
                    currentColor = Color(allSettings.colorOfPrice)
                    currentFontSize = allSettings.fontSizeOfPrice
                    currentFontFamily = allSettings.fontFamilyOfPrice
                    currentDecoration = allSettings.decorationOfPrice
                    currentColorOfRowBackground = Color(allSettings.colorOfRowBackground)
                    currentColorOfRowBackgroundActive = Color(allSettings.colorOfRowBackgroundActive)
                    currentColorOfProviderSecond = Color(allSettings.colorOfProviderSecond)
                    currentColorOfHighlightBackground = Color(allSettings.colorOfHighlightBackground)
                    visiblePickers = VisiblePicker(
                        colorPicker = true,
                        fontSizePicker = true,
                        fontFamilyPicker = true,
                        decorationPicker = true,
                        colorOfProviderSecondPicker = false,
                        colorOfRowBackgroundPicker = false,
                        colorOfRowBackgroundActivePicker = false,
                        colorOfProviderBackgroundPicker = false,
                        colorOfHighlightBackgroundPicker = false,
                    )
                }

                DesignS.QUANTITY_DESIGN -> {
                    currentColor = Color(allSettings.colorOfQuantity)
                    currentFontSize = allSettings.fontSizeOfQuantity
                    currentFontFamily = allSettings.fontFamilyOfQuantity
                    currentDecoration = allSettings.decorationOfQuantity
                    currentColorOfRowBackground = Color(allSettings.colorOfRowBackground)
                    currentColorOfRowBackgroundActive = Color(allSettings.colorOfRowBackgroundActive)
                    currentColorOfProviderSecond = Color(allSettings.colorOfProviderSecond)
                    currentColorOfHighlightBackground = Color(allSettings.colorOfHighlightBackground)
                    visiblePickers = VisiblePicker(
                        colorPicker = true,
                        fontSizePicker = true,
                        fontFamilyPicker = true,
                        decorationPicker = true,
                        colorOfProviderSecondPicker = false,
                        colorOfRowBackgroundPicker = false,
                        colorOfRowBackgroundActivePicker = false,
                        colorOfProviderBackgroundPicker = false,
                        colorOfHighlightBackgroundPicker = false,
                    )
                }

                DesignS.PROVIDER_DESIGN -> {
                    currentColor = Color(allSettings.colorOfProvider)
                    currentFontSize = allSettings.fontSizeOfProvider
                    currentFontFamily = allSettings.fontFamilyOfProvider
                    currentDecoration = allSettings.decorationOfProvider
                    currentColorOfProviderSecond = Color(allSettings.colorOfProviderSecond)
                    currentColorOfProviderBackground = Color(allSettings.colorOfProviderBackground)
                    currentColorOfRowBackground = Color(allSettings.colorOfRowBackground)
                    currentColorOfRowBackgroundActive = Color(allSettings.colorOfRowBackgroundActive)
                    currentColorOfProviderSecond = Color(allSettings.colorOfProviderSecond)
                    currentColorOfHighlightBackground = Color(allSettings.colorOfHighlightBackground)
                    visiblePickers = VisiblePicker(
                        colorPicker = true,
                        fontSizePicker = true,
                        fontFamilyPicker = true,
                        decorationPicker = true,
                        colorOfProviderSecondPicker = true,
                        colorOfRowBackgroundPicker = false,
                        colorOfRowBackgroundActivePicker = false,
                        colorOfProviderBackgroundPicker = true,
                        colorOfHighlightBackgroundPicker = false,
                    )
                }

                DesignS.BACKGROUND_DESIGN -> {
                    currentColor = Color.Transparent
                    currentColorOfRowBackground = Color(allSettings.colorOfRowBackground)
                    currentColorOfRowBackgroundActive = Color(allSettings.colorOfRowBackgroundActive)
                    currentColorOfProviderSecond = Color(allSettings.colorOfProviderSecond)
                    currentColorOfHighlightBackground = Color(allSettings.colorOfHighlightBackground)
                    visiblePickers = VisiblePicker(
                        colorPicker = false,
                        fontSizePicker = false,
                        fontFamilyPicker = false,
                        decorationPicker = false,
                        colorOfProviderSecondPicker = false,
                        colorOfRowBackgroundPicker = true,
                        colorOfRowBackgroundActivePicker = true,
                        colorOfProviderBackgroundPicker = false,
                        colorOfHighlightBackgroundPicker = false,
                    )
                }

                DesignS.HIGHLIGHT_DESIGN -> {
                    currentColor = Color(allSettings.colorOfHighlight)
                    currentFontSize = allSettings.fontSizeOfHighlight
                    currentFontFamily = allSettings.fontFamilyOfHighlight
                    currentDecoration = allSettings.decorationOfHighlight
                    currentColorOfRowBackground = Color(allSettings.colorOfRowBackground)
                    currentColorOfRowBackgroundActive = Color(allSettings.colorOfRowBackgroundActive)
                    currentColorOfProviderSecond = Color(allSettings.colorOfProviderSecond)
                    currentColorOfHighlightBackground = Color(allSettings.colorOfHighlightBackground)
                    visiblePickers = VisiblePicker(
                        colorPicker = true,
                        fontSizePicker = true,
                        fontFamilyPicker = true,
                        decorationPicker = true,
                        colorOfProviderSecondPicker = false,
                        colorOfRowBackgroundPicker = false,
                        colorOfRowBackgroundActivePicker = false,
                        colorOfProviderBackgroundPicker = false,
                        colorOfHighlightBackgroundPicker = true,
                    )
                }
            }
            key(itemToDesign.value) {
                LazyColumn {


//  ___ Color Picker ___
                    if (visiblePickers.colorPicker) {
                        stickyHeader {
                            DesignTitle(stringResource(R.string.color))
                        }
                        item {
                            ColorPickerBlock(
                                initialColor = currentColor,
                                controller = controller,
                                onColorChange = {
                                    scope.launch {
                                        settingStoreManager.saveColor(
                                            itemToDesign.value,
                                            it.toArgb()
                                        )
                                    }
                                }
                            )
                        }
                    }

//  ___ Color Of Provider Second ___
                    if (visiblePickers.colorOfProviderSecondPicker) {
                        stickyHeader {
                            DesignTitle(stringResource(R.string.color_of_different_provider))
                        }
                        item {
                            ColorPickerBlock(
                                initialColor = currentColorOfProviderSecond,
                                controller = controllerProviderSecond,
                                onColorChange = {
                                    scope.launch {
                                        settingStoreManager.saveColorOfProviderSecond(
                                            it.toArgb()
                                        )
                                    }
                                }
                            )
                        }
                    }

//  ___ Color Of Highlight Background ___
                    if (visiblePickers.colorOfHighlightBackgroundPicker) {
                        stickyHeader {
                            DesignTitle(stringResource(R.string.color_of_highlight_background))
                        }
                        item {
                            ColorPickerBlock(
                                initialColor = currentColorOfHighlightBackground,
                                controller = controllerHighlightBackground,
                                onColorChange = {
                                    scope.launch {
                                        settingStoreManager.saveColorOfHighlightBackground(
                                            it.toArgb()
                                        )
                                    }
                                }
                            )
                        }
                    }

//  ___ Color Of Provider Background ___
                    if (visiblePickers.colorOfProviderBackgroundPicker && displayType == DisplayType.PROVIDER_HEADER) {
                        stickyHeader {
                            DesignTitle(stringResource(R.string.setting_color_of_provider_background))
                        }
                        item {
                            ColorPickerBlock(
                                initialColor = currentColorOfProviderBackground,
                                controller = controllerProviderBackground,
                                onColorChange = {
                                    scope.launch {
                                        settingStoreManager.saveColorOfProviderBackground(
                                            it.toArgb()
                                        )
                                    }
                                }
                            )
                        }
                    }

//  ___ Font Size Picker ___
                    if (visiblePickers.fontSizePicker) {
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
                                                itemToDesign.value,
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
                                                itemToDesign.value,
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
                    if (visiblePickers.fontFamilyPicker) {
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
                                                itemToDesign.value,
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
                                                itemToDesign.value,
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
                                                itemToDesign.value,
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
                                                itemToDesign.value,
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
                    if (visiblePickers.decorationPicker) {
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
                                    tint = if (currentDecoration[0] == '1') {
                                        MaterialTheme.colorScheme.onTertiary
                                    } else MaterialTheme.colorScheme.onPrimary,
                                    onClick = {
                                        val firstChar = if (currentDecoration[0] == '0') '1' else '0'
                                        val secondChar = currentDecoration[1].toString()
                                        val thirdChar = currentDecoration[2].toString()
                                        val newDecoration = firstChar + secondChar + thirdChar
                                        scope.launch {
                                            settingStoreManager.saveDecoration(
                                                itemToDesign.value,
                                                newDecoration
                                            )
                                        }
                                    },
                                )
                                ButtonWithIcon(
                                    ButtonType.SMALL,
                                    R.drawable.italic_icon,
                                    tint = if (currentDecoration[1] == '1') {
                                        MaterialTheme.colorScheme.onTertiary
                                    } else MaterialTheme.colorScheme.onPrimary,
                                    onClick = {
                                        val firstChar = currentDecoration[0].toString()
                                        val secondChar = if (currentDecoration[1] == '0') '1' else '0'
                                        val thirdChar = currentDecoration[2].toString()
                                        val newDecoration = firstChar + secondChar + thirdChar
                                        scope.launch {
                                            settingStoreManager.saveDecoration(
                                                itemToDesign.value,
                                                newDecoration
                                            )
                                        }
                                    },
                                )
                                ButtonWithIcon(
                                    ButtonType.SMALL,
                                    R.drawable.underline_icon,
                                    tint = if (currentDecoration[2] == '1') {
                                        MaterialTheme.colorScheme.onTertiary
                                    } else MaterialTheme.colorScheme.onPrimary,
                                    onClick = {
                                        val firstChar = currentDecoration[0].toString()
                                        val secondChar = currentDecoration[1].toString()
                                        val thirdChar = if (currentDecoration[2] == '0') '1' else '0'
                                        val newDecoration = firstChar + secondChar + thirdChar
                                        scope.launch {
                                            settingStoreManager.saveDecoration(
                                                itemToDesign.value,
                                                newDecoration
                                            )
                                        }
                                    },
                                )
                            }
                        }
                    }

//  ___ Color Of Row Background ___
                    if (visiblePickers.colorOfRowBackgroundPicker) {
                        stickyHeader {
                            DesignTitle(stringResource(R.string.setting_color_of_row_background))
                        }
                        item {
                            ColorPickerBlock(
                                initialColor = currentColorOfRowBackground,
                                controller = controllerRowBackground,
                                onColorChange = {
                                    scope.launch {
                                        settingStoreManager.saveColorOfRowBackground(
                                            it.toArgb()
                                        )
                                    }
                                }
                            )
                        }
                    }
//  ___ Color Of Row Background Active ___
                    if (visiblePickers.colorOfRowBackgroundActivePicker) {
                        stickyHeader {
                            DesignTitle(stringResource(R.string.setting_color_of_row_background_active))
                        }
                        item {
                            ColorPickerBlock(
                                initialColor = currentColorOfRowBackgroundActive,
                                controller = controllerRowBackgroundActive,
                                onColorChange = {
                                    scope.launch {
                                        settingStoreManager.saveColorOfRowBackgroundActive(
                                            it.toArgb()
                                        )
                                    }
                                }
                            )
                        }
                    }

//  ___ Reset To Default ___
                    stickyHeader {
                        DesignTitle(stringResource(R.string.reset_to_default))
                    }
                    item {
                        ButtonTextS(
                            ButtonType.SMALL,
                            stringResource(R.string.reset_to_default),
                            fontFamily = Font.robotoMedium,
                            color = MaterialTheme.colorScheme.onPrimary,
                            onClick = {
                                scope.launch {
                                    when (itemToDesign.value) {
                                        DesignS.PRODUCT_DESIGN -> {
                                            settingStoreManager.saveColor(
                                                itemToDesign.value,
                                                DesignS.default.getValue(DesignS.COLOR_OF_PRODUCT_ID) as Int
                                            )
                                            settingStoreManager.saveFontSize(
                                                itemToDesign.value,
                                                DesignS.default.getValue(DesignS.FONT_SIZE_OF_PRODUCT_ID) as Int
                                            )
                                            settingStoreManager.saveFontFamily(
                                                itemToDesign.value,
                                                DesignS.default.getValue(DesignS.FONT_FAMILY_OF_PRODUCT_ID) as String
                                            )
                                            settingStoreManager.saveDecoration(
                                                itemToDesign.value,
                                                DesignS.default.getValue(DesignS.DECORATION_OF_PRODUCT_ID) as String
                                            )
                                        }

                                        DesignS.PRICE_DESIGN -> {
                                            settingStoreManager.saveColor(
                                                itemToDesign.value,
                                                DesignS.default.getValue(DesignS.COLOR_OF_PRICE_ID) as Int
                                            )
                                            settingStoreManager.saveFontSize(
                                                itemToDesign.value,
                                                DesignS.default.getValue(DesignS.FONT_SIZE_OF_PRICE_ID) as Int
                                            )
                                            settingStoreManager.saveFontFamily(
                                                itemToDesign.value,
                                                DesignS.default.getValue(DesignS.FONT_FAMILY_OF_PRICE_ID) as String
                                            )
                                            settingStoreManager.saveDecoration(
                                                itemToDesign.value,
                                                DesignS.default.getValue(DesignS.DECORATION_OF_PRICE_ID) as String
                                            )
                                        }

                                        DesignS.QUANTITY_DESIGN -> {
                                            settingStoreManager.saveColor(
                                                itemToDesign.value,
                                                DesignS.default.getValue(DesignS.COLOR_OF_QUANTITY_ID) as Int
                                            )
                                            settingStoreManager.saveFontSize(
                                                itemToDesign.value,
                                                DesignS.default.getValue(DesignS.FONT_SIZE_OF_QUANTITY_ID) as Int
                                            )
                                            settingStoreManager.saveFontFamily(
                                                itemToDesign.value,
                                                DesignS.default.getValue(DesignS.FONT_FAMILY_OF_QUANTITY_ID) as String
                                            )
                                            settingStoreManager.saveDecoration(
                                                itemToDesign.value,
                                                DesignS.default.getValue(DesignS.DECORATION_OF_QUANTITY_ID) as String
                                            )
                                        }

                                        DesignS.PROVIDER_DESIGN -> {
                                            settingStoreManager.saveColor(
                                                itemToDesign.value,
                                                DesignS.default.getValue(DesignS.COLOR_OF_PROVIDER_ID) as Int
                                            )
                                            settingStoreManager.saveFontSize(
                                                itemToDesign.value,
                                                DesignS.default.getValue(DesignS.FONT_SIZE_OF_PROVIDER_ID) as Int
                                            )
                                            settingStoreManager.saveFontFamily(
                                                itemToDesign.value,
                                                DesignS.default.getValue(DesignS.FONT_FAMILY_OF_PROVIDER_ID) as String
                                            )
                                            settingStoreManager.saveDecoration(
                                                itemToDesign.value,
                                                DesignS.default.getValue(DesignS.DECORATION_OF_PROVIDER_ID) as String
                                            )
                                            settingStoreManager.saveColorOfProviderBackground(
                                                DesignS.default.getValue(DesignS.COLOR_OF_PROVIDER_BACKGROUND_ID) as Int
                                            )
                                            settingStoreManager.saveColorOfProviderSecond(
                                                DesignS.default.getValue(DesignS.COLOR_OF_PROVIDER_SECOND_ID) as Int
                                            )
                                        }

                                        DesignS.BACKGROUND_DESIGN -> {
                                            settingStoreManager.saveColorOfRowBackground(
                                                DesignS.default.getValue(DesignS.COLOR_OF_ROW_BACKGROUND_ID) as Int
                                            )
                                            settingStoreManager.saveColorOfRowBackgroundActive(
                                                DesignS.default.getValue(DesignS.COLOR_OF_ROW_BACKGROUND_ACTIVE_ID) as Int
                                            )
                                        }

                                        DesignS.HIGHLIGHT_DESIGN -> {
                                            settingStoreManager.saveColor(
                                                itemToDesign.value,
                                                DesignS.default.getValue(DesignS.COLOR_OF_HIGHLIGHT_ID) as Int
                                            )
                                            settingStoreManager.saveFontSize(
                                                itemToDesign.value,
                                                DesignS.default.getValue(DesignS.FONT_SIZE_OF_HIGHLIGHT_ID) as Int
                                            )
                                            settingStoreManager.saveFontFamily(
                                                itemToDesign.value,
                                                DesignS.default.getValue(DesignS.FONT_FAMILY_OF_HIGHLIGHT_ID) as String
                                            )
                                            settingStoreManager.saveDecoration(
                                                itemToDesign.value,
                                                DesignS.default.getValue(DesignS.DECORATION_OF_HIGHLIGHT_ID) as String
                                            )
                                            settingStoreManager.saveColorOfHighlightBackground(
                                                DesignS.default.getValue(DesignS.COLOR_OF_HIGHLIGHT_BACKGROUND_ID) as Int
                                            )
                                        }
                                    }
                                }
                            }
                        )
                    }
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
package com.seryoga.sturmstorages.model

/**
 * Save complete setting data
 * */
data class SettingData(
    var themeType: Boolean = ThemeS.DARK,
    var displayType: Int = DisplayType.ALL_IN_ROW,
    var hryvniaSign: Boolean = HryvniaSign.HIDE_HRYVNA_SIGN,
    var colorOfProduct: Int = 0,
    var fontSizeOfProduct: Int = 0,
    var fontFamilyOfProduct: String = "",
    var fontStyleOfProduct: Boolean = false,

    var colorOfPrice: Int = 0,
    var fontSizeOfPrice: Int = 0,
    var fontFamilyOfPrice: String = "",
    var fontStyleOfPrice: Boolean = false,

    var colorOfQuantity: Int = 0,
    var fontSizeOfQuantity: Int = 0,
    var fontFamilyOfQuantity: String = "",
    var fontStyleOfQuantity: Boolean = false,

    var colorOfProvider: Int = 0,
    var fontSizeOfProvider: Int = 0,
    var fontFamilyOfProvider: String = "",
    var fontStyleOfProvider: Boolean = false,

    var colorOfProviderSecond: Int = 0,
    var colorOfProviderBackground: Int = 0,
    var colorOfRowBackground: Int = 0,
    var colorOfRowBackgroundActive: Int = 0,
)
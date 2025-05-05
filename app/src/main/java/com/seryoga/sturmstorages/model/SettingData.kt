package com.seryoga.sturmstorages.model

/**
 * Save whole setting data with default value
 **/

/**
 * TODO: make map and fun for easily access to Items displayed
 **/
data class SettingData(

    var url: String = DataS.default[DataS.URL_ID] as String,
    var iSensitive: Boolean = DataS.default[DataS.I_SENSITIVE_ID] as Boolean,

    var themeType: Boolean = DisplayS.default[DisplayS.THEME_ID] as Boolean,
    var displayType: Int = DisplayS.default[DisplayS.DISPLAY_ID] as Int,
    var hryvniaSign: Boolean = DisplayS.default[DisplayS.HRYVNIA_SIGN_ID] as Boolean,

    var colorOfProduct: Int = DesignS.default[DesignS.COLOR_OF_PRODUCT_ID] as Int,
    var fontSizeOfProduct: Int = DesignS.default[DesignS.FONT_SIZE_OF_PRODUCT_ID] as Int,
    var fontFamilyOfProduct: String = DesignS.default[DesignS.FONT_FAMILY_OF_PRODUCT_ID] as String,

    var colorOfPrice: Int = DesignS.default[DesignS.COLOR_OF_PRICE_ID] as Int,
    var fontSizeOfPrice: Int = DesignS.default[DesignS.FONT_SIZE_OF_PRICE_ID] as Int,
    var fontFamilyOfPrice: String = DesignS.default[DesignS.FONT_FAMILY_OF_PRICE_ID] as String,

    var colorOfQuantity: Int = DesignS.default[DesignS.COLOR_OF_QUANTITY_ID] as Int,
    var fontSizeOfQuantity: Int = DesignS.default[DesignS.FONT_SIZE_OF_QUANTITY_ID] as Int,
    var fontFamilyOfQuantity: String = DesignS.default[DesignS.FONT_FAMILY_OF_QUANTITY_ID] as String,

    var colorOfProvider: Int = DesignS.default[DesignS.COLOR_OF_PROVIDER_ID] as Int,
    var colorOfProviderSecond: Int = DesignS.default[DesignS.COLOR_OF_PROVIDER_SECOND_ID] as Int,
    var fontSizeOfProvider: Int = DesignS.default[DesignS.FONT_SIZE_OF_PROVIDER_ID] as Int,
    var fontFamilyOfProvider: String = DesignS.default[DesignS.FONT_FAMILY_OF_PROVIDER_ID] as String,

    var colorOfProviderBackground: Int = DesignS.default[DesignS.COLOR_OF_PROVIDER_BACKGROUND_ID] as Int,

    var colorOfRowBackground: Int = DesignS.default[DesignS.COLOR_OF_ROW_BACKGROUND_ID] as Int,
    var colorOfRowBackgroundActive: Int = DesignS.default[DesignS.COLOR_OF_ROW_BACKGROUND_ACTIVE_ID] as Int,
)

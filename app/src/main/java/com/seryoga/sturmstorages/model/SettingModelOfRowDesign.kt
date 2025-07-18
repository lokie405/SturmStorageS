package com.seryoga.sturmstorages.model

data class SettingModelOfRowDesign(
    val colorOfProduct: Int = DesignS.default[DesignS.COLOR_OF_PRODUCT_ID] as Int,
    val fontSizeOfProduct: Int = DesignS.default[DesignS.FONT_SIZE_OF_PRODUCT_ID] as Int,
    val fontFamilyOfProduct: String = DesignS.default[DesignS.FONT_FAMILY_OF_PRODUCT_ID] as String,
    val decorationOfProduct: String = DesignS.default[DesignS.DECORATION_OF_PRODUCT_ID] as String,

//    val colorOfPrice: Int,
//    val fontSizeOfPrice: Int,
//    val fontFamilyOfPrice: String,
//    val decorationOfPrice: String,
//
//    val colorOfQuantity: Int,
//    val fontSizeOfQuantity: Int,
//    val fontFamilyOfQuantity: String,
//    val decorationOfQuantity: String,
//
//    val colorOfProvider: Int,
//    val fontSizeOfProvider: Int,
//    val fontFamilyOfProvider: String,
//    val decorationOfProvider: String,
//
//    val colorOfHighlight: Int,
//    val fontSizeOfHighlight: Int,
//    val fontFamilyOfHighlight: String,
//    val decorationOfHighlight: String,
//
//    val colorOfProviderSecond: Int,
//    val colorOfProviderBackground: Int,
//    val colorOfRowBackground: Int,
//    val colorOfRowBackgroundActive: Int,

//    val colorOfProvidHighlight: Int,
)

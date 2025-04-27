package com.seryoga.sturmstorages.model

import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.seryoga.sturmstorages.ui.theme.Cardboard
import com.seryoga.sturmstorages.ui.theme.ColorBlue
import com.seryoga.sturmstorages.ui.theme.ColorMagenta
import com.seryoga.sturmstorages.ui.theme.DarkGrey
import com.seryoga.sturmstorages.ui.theme.DarkestGrey
import com.seryoga.sturmstorages.ui.theme.Dollar
import com.seryoga.sturmstorages.ui.theme.Font
import com.seryoga.sturmstorages.ui.theme.MilkGrey
import com.seryoga.sturmstorages.ui.theme.Silver

//object DS {
//    object PRODUCT {
//
//    }
//}

object DesignS{
    const val PRODUCT_DESIGN = "product_design"
    const val COLOR_OF_PRODUCT_ID = "color_of_product"
    val COLOR_OF_PRODUCT_PREFERENCES_KEY = intPreferencesKey(COLOR_OF_PRODUCT_ID)

    const val FONT_SIZE_OF_PRODUCT_ID = "font_size_of_product"
    val FONT_SIZE_OF_PRODUCT_PREFERENCES_KEY = intPreferencesKey(FONT_SIZE_OF_PRODUCT_ID)

    const val FONT_FAMILY_OF_PRODUCT_ID = "font_family_of_product"
    val FONT_FAMILY_OF_PRODUCT_PREFERENCES_KEY = stringPreferencesKey(FONT_FAMILY_OF_PRODUCT_ID)

//    const val FONT_STYLE_OF_PRODUCT_ID = "font_style_of_product"
//    val FONT_STYLE_OF_PRODUCT_PREFERENCES_KEY = booleanPreferencesKey(FONT_STYLE_OF_PRODUCT_ID)


    const val PRICE_DESIGN = "price_design"
    const val COLOR_OF_PRICE_ID = "color_of_price"
    val COLOR_OF_PRICE_PREFERENCES_KEY = intPreferencesKey(COLOR_OF_PRICE_ID)

    const val FONT_SIZE_OF_PRICE_ID = "font_size_of_price"
    val FONT_SIZE_OF_PRICE_PREFERENCES_KEY = intPreferencesKey(FONT_SIZE_OF_PRICE_ID)

    const val FONT_FAMILY_OF_PRICE_ID = "font_family_of_price"
    val FONT_FAMILY_OF_PRICE_PREFERENCES_KEY = stringPreferencesKey(FONT_FAMILY_OF_PRICE_ID)

//    const val FONT_STYLE_OF_PRICE_ID = "font_style_of_price"
//    val FONT_STYLE_OF_PRICE_PREFERENCES_KEY = booleanPreferencesKey(FONT_STYLE_OF_PRICE_ID)


    const val QUANTITY_DESIGN = "quantity_design"
    const val COLOR_OF_QUANTITY_ID = "color_of_quantity"
    val COLOR_OF_QUANTITY_PREFERENCES_KEY = intPreferencesKey(COLOR_OF_QUANTITY_ID)

    const val FONT_SIZE_OF_QUANTITY_ID = "font_size_of_quantity"
    val FONT_SIZE_OF_QUANTITY_PREFERENCES_KEY = intPreferencesKey(FONT_SIZE_OF_QUANTITY_ID)

    const val FONT_FAMILY_OF_QUANTITY_ID = "font_family_of_quantity"
    val FONT_FAMILY_OF_QUANTITY_PREFERENCES_KEY = stringPreferencesKey(FONT_FAMILY_OF_QUANTITY_ID)

//    const val FONT_STYLE_OF_QUANTITY_ID = "font_style_of_quantity"
//    val FONT_STYLE_OF_QUANTITY_PREFERENCES_KEY = booleanPreferencesKey(FONT_STYLE_OF_QUANTITY_ID)


    const val PROVIDER_DESIGN = "provider_design"
    const val COLOR_OF_PROVIDER_ID = "color_of_provider"
    val COLOR_OF_PROVIDER_PREFERENCES_KEY = intPreferencesKey(COLOR_OF_PROVIDER_ID)

    const val FONT_SIZE_OF_PROVIDER_ID = "font_size_of_provider"
    val FONT_SIZE_OF_PROVIDER_PREFERENCES_KEY = intPreferencesKey(FONT_SIZE_OF_PROVIDER_ID)

    const val FONT_FAMILY_OF_PROVIDER_ID = "font_family_of_provider"
    val FONT_FAMILY_OF_PROVIDER_PREFERENCES_KEY = stringPreferencesKey(FONT_FAMILY_OF_PROVIDER_ID)

//    const val FONT_STYLE_OF_PROVIDER_ID = "font_style_of_provider"
//    val FONT_STYLE_OF_PROVIDER_PREFERENCES_KEY = booleanPreferencesKey(FONT_STYLE_OF_PROVIDER_ID)


    const val PROVIDER_SECOND_DESIGN = "provider_second_design"
    const val COLOR_OF_PROVIDER_SECOND_ID = "color_of_provider_second"
    val COLOR_OF_PROVIDER_SECOND_PREFERENCES_KEY = intPreferencesKey(COLOR_OF_PROVIDER_SECOND_ID)

    const val COLOR_OF_PROVIDER_BACKGROUND_ID = "color_of_provider_background"
    val COLOR_OF_PROVIDER_BACKGROUND_PREFERENCES_KEY = intPreferencesKey(COLOR_OF_PROVIDER_BACKGROUND_ID)

    const val COLOR_OF_ROW_BACKGROUND_ID = "color_of_row_background"
    val COLOR_OF_ROW_BACKGROUND_PREFERENCES_KEY = intPreferencesKey(COLOR_OF_ROW_BACKGROUND_ID)

    const val COLOR_OF_ROW_BACKGROUND_ACTIVE_ID = "color_of_row_background_active"
    val COLOR_OF_ROW_BACKGROUND_ACTIVE_PREFERENCES_KEY = intPreferencesKey(COLOR_OF_ROW_BACKGROUND_ACTIVE_ID)

    val map = mapOf(
        PRODUCT_DESIGN to listOf(COLOR_OF_PRODUCT_ID, FONT_SIZE_OF_PRODUCT_ID, FONT_FAMILY_OF_PRODUCT_ID, /*FONT_STYLE_OF_PRODUCT_ID*/),
        PRICE_DESIGN to listOf(COLOR_OF_PRICE_ID, FONT_SIZE_OF_PRICE_ID, FONT_FAMILY_OF_PRICE_ID, /*FONT_STYLE_OF_PRICE_ID*/),
        QUANTITY_DESIGN to listOf(COLOR_OF_QUANTITY_ID, FONT_SIZE_OF_QUANTITY_ID, FONT_FAMILY_OF_QUANTITY_ID, /*FONT_STYLE_OF_QUANTITY_ID*/),
        PROVIDER_DESIGN to listOf(COLOR_OF_PROVIDER_ID, FONT_SIZE_OF_PROVIDER_ID, FONT_FAMILY_OF_PROVIDER_ID, /*FONT_STYLE_OF_PROVIDER_ID*/),
        PROVIDER_SECOND_DESIGN to listOf(COLOR_OF_PROVIDER_SECOND_ID, FONT_SIZE_OF_PROVIDER_ID, FONT_FAMILY_OF_PROVIDER_ID, /*FONT_STYLE_OF_PROVIDER_ID*/),
        COLOR_OF_PROVIDER_BACKGROUND_ID to listOf(COLOR_OF_PROVIDER_BACKGROUND_ID),
        COLOR_OF_ROW_BACKGROUND_ID to listOf(COLOR_OF_ROW_BACKGROUND_ID),
        COLOR_OF_ROW_BACKGROUND_ACTIVE_ID to listOf(COLOR_OF_ROW_BACKGROUND_ACTIVE_ID),
    )

    val default = mapOf(
        COLOR_OF_PRODUCT_ID to MilkGrey.toArgb(),
        FONT_SIZE_OF_PRODUCT_ID to 14,
        FONT_FAMILY_OF_PRODUCT_ID to Font.JET_BRAIN,
        COLOR_OF_PRICE_ID to Dollar.toArgb(),
        FONT_SIZE_OF_PRICE_ID to 16,
        FONT_FAMILY_OF_PRICE_ID to Font.JET_BRAIN,
        COLOR_OF_QUANTITY_ID to Cardboard.toArgb(),
        FONT_SIZE_OF_QUANTITY_ID to 15,
        FONT_FAMILY_OF_QUANTITY_ID to Font.JET_BRAIN,
        COLOR_OF_PROVIDER_ID to ColorBlue.toArgb(),
        COLOR_OF_PROVIDER_SECOND_ID to ColorMagenta.toArgb(),
        FONT_SIZE_OF_PROVIDER_ID to 14,
        FONT_FAMILY_OF_PROVIDER_ID to Font.JET_BRAIN,
        COLOR_OF_PROVIDER_BACKGROUND_ID to Silver.toArgb(),
        COLOR_OF_ROW_BACKGROUND_ID to DarkGrey.toArgb(),
        COLOR_OF_ROW_BACKGROUND_ACTIVE_ID to DarkestGrey.toArgb(),


    )

}

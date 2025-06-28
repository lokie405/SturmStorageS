package com.seryoga.sturmstorages.model

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.seryoga.sturmstorages.R
import com.seryoga.sturmstorages.ui.theme.Cardboard
import com.seryoga.sturmstorages.ui.theme.ColorBlue
import com.seryoga.sturmstorages.ui.theme.ColorMagenta
import com.seryoga.sturmstorages.ui.theme.ColorRed
import com.seryoga.sturmstorages.ui.theme.DarkGrey
import com.seryoga.sturmstorages.ui.theme.DarkestGrey
import com.seryoga.sturmstorages.ui.theme.Dollar
import com.seryoga.sturmstorages.ui.theme.Font
import com.seryoga.sturmstorages.ui.theme.Milk
import settingStore

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

    const val HIGHLIGHT_DESIGN = "highlight_design"
    const val COLOR_OF_HIGHLIGHT_ID = "color_of_highlight"
    val COLOR_OF_HIGHLIGHT_PREFERENCES_KEY = intPreferencesKey(COLOR_OF_HIGHLIGHT_ID)

    const val FONT_SIZE_OF_HIGHLIGHT_ID = "font_size_of_highlight"
    val FONT_SIZE_OF_HIGHLIGHT_PREFERENCE_KEY = intPreferencesKey(FONT_SIZE_OF_HIGHLIGHT_ID)

    const val FONT_FAMILY_OF_HIGHLIGHT_ID = "font_family_of_highlight"
    val FONT_FAMILY_OF_HIGHLIGHT_PREFERENCE_KEY = stringPreferencesKey(FONT_FAMILY_OF_HIGHLIGHT_ID)

    const val COLOR_OF_HIGHLIGHT_BACKGROUND_ID = "color_of_highlight_background"
    val COLOR_OF_HIGHLIGHT_BACKGROUND_PREFERENCE_KEY = intPreferencesKey(COLOR_OF_HIGHLIGHT_BACKGROUND_ID)

    const val TEXT_DECORATION_OF_HIGHLIGHT_ID = "text_decoration_of_highlight"
    val TEXT_DECORATION_OF_HIGHLIGHT_PREFERENCE_KEY = booleanPreferencesKey(TEXT_DECORATION_OF_HIGHLIGHT_ID)

    val titleAndIcons = mapOf<String, List<Int>>(
        PRODUCT_DESIGN to listOf(R.string.setting_design_of_product, R.drawable.box_icon),
        PRICE_DESIGN to listOf(R.string.setting_design_of_price, R.drawable.wallet_icon),
        QUANTITY_DESIGN to listOf(R.string.setting_design_of_quantity, R.drawable.boxes_icon),
        PROVIDER_DESIGN to listOf(R.string.setting_design_of_provider, R.drawable.truck_icon),
//        PROVIDER_SECOND_DESIGN to R.string.setting_color_of_provider_second,
//        COLOR_OF_PROVIDER_BACKGROUND_ID to R.string.setting_color_of_provider_background,
        COLOR_OF_ROW_BACKGROUND_ID to listOf(R.string.setting_color_of_row_background, R.drawable.background_color_iconsvgrepo_com),
//        COLOR_OF_ROW_BACKGROUND_ACTIVE_ID to R.string.setting_color_of_row_background_active,
        HIGHLIGHT_DESIGN to listOf(R.string.setting_highlight, R.drawable.marker_icon),




    )

    val map: Map<String, List<String>> = mapOf(
        PRODUCT_DESIGN to listOf(COLOR_OF_PRODUCT_ID, FONT_SIZE_OF_PRODUCT_ID, FONT_FAMILY_OF_PRODUCT_ID, /*FONT_STYLE_OF_PRODUCT_ID*/),
        PRICE_DESIGN to listOf(COLOR_OF_PRICE_ID, FONT_SIZE_OF_PRICE_ID, FONT_FAMILY_OF_PRICE_ID, /*FONT_STYLE_OF_PRICE_ID*/),
        QUANTITY_DESIGN to listOf(COLOR_OF_QUANTITY_ID, FONT_SIZE_OF_QUANTITY_ID, FONT_FAMILY_OF_QUANTITY_ID, /*FONT_STYLE_OF_QUANTITY_ID*/),
        PROVIDER_DESIGN to listOf(COLOR_OF_PROVIDER_ID, FONT_SIZE_OF_PROVIDER_ID, FONT_FAMILY_OF_PROVIDER_ID, COLOR_OF_PROVIDER_BACKGROUND_ID /*FONT_STYLE_OF_PROVIDER_ID*/),
        PROVIDER_SECOND_DESIGN to listOf(COLOR_OF_PROVIDER_SECOND_ID, FONT_SIZE_OF_PROVIDER_ID, FONT_FAMILY_OF_PROVIDER_ID, COLOR_OF_PROVIDER_BACKGROUND_ID /*FONT_STYLE_OF_PROVIDER_ID*/),
//        COLOR_OF_PROVIDER_BACKGROUND_ID to listOf("", "", "", COLOR_OF_PROVIDER_BACKGROUND_ID),
        COLOR_OF_ROW_BACKGROUND_ID to listOf("", "", "", COLOR_OF_ROW_BACKGROUND_ID),
        COLOR_OF_ROW_BACKGROUND_ACTIVE_ID to listOf("", "", "", COLOR_OF_ROW_BACKGROUND_ACTIVE_ID),
        HIGHLIGHT_DESIGN to listOf(COLOR_OF_HIGHLIGHT_ID, FONT_SIZE_OF_HIGHLIGHT_ID, FONT_FAMILY_OF_HIGHLIGHT_ID, COLOR_OF_HIGHLIGHT_BACKGROUND_ID, TEXT_DECORATION_OF_HIGHLIGHT_ID)
    )

    suspend fun resetDesignToDefault(context: Context){
        context.settingStore.edit { pref ->
            pref[intPreferencesKey(COLOR_OF_PRODUCT_ID)] = default[COLOR_OF_PRODUCT_ID] as Int
            pref[intPreferencesKey(FONT_SIZE_OF_PRODUCT_ID)] = default[FONT_SIZE_OF_PRODUCT_ID] as Int
            pref[stringPreferencesKey(FONT_FAMILY_OF_PRODUCT_ID)] = default[FONT_FAMILY_OF_PRODUCT_ID] as String

            pref[intPreferencesKey(COLOR_OF_PRICE_ID)] = default[COLOR_OF_PRICE_ID] as Int
            pref[intPreferencesKey(FONT_SIZE_OF_PRICE_ID)] = default[FONT_SIZE_OF_PRICE_ID] as Int
            pref[stringPreferencesKey(FONT_FAMILY_OF_PRICE_ID)] = default[FONT_FAMILY_OF_PRICE_ID] as String

            pref[intPreferencesKey(COLOR_OF_QUANTITY_ID)] = default[COLOR_OF_QUANTITY_ID] as Int
            pref[intPreferencesKey(FONT_SIZE_OF_QUANTITY_ID)] = default[FONT_SIZE_OF_QUANTITY_ID] as Int
            pref[stringPreferencesKey(FONT_FAMILY_OF_QUANTITY_ID)] = default[FONT_FAMILY_OF_QUANTITY_ID] as String

            pref[intPreferencesKey(COLOR_OF_PROVIDER_ID)] = default[COLOR_OF_PROVIDER_ID] as Int
            pref[intPreferencesKey(FONT_SIZE_OF_PROVIDER_ID)] = default[FONT_SIZE_OF_PROVIDER_ID] as Int
            pref[stringPreferencesKey(FONT_FAMILY_OF_PROVIDER_ID)] = default[FONT_FAMILY_OF_PROVIDER_ID] as String
            pref[intPreferencesKey(COLOR_OF_PROVIDER_SECOND_ID)] = default[COLOR_OF_PROVIDER_SECOND_ID] as Int
            pref[intPreferencesKey(COLOR_OF_PROVIDER_BACKGROUND_ID)] = default[COLOR_OF_PROVIDER_BACKGROUND_ID] as Int
            pref[intPreferencesKey(COLOR_OF_ROW_BACKGROUND_ID)] = default[COLOR_OF_ROW_BACKGROUND_ID] as Int
            pref[intPreferencesKey(COLOR_OF_ROW_BACKGROUND_ACTIVE_ID)] = default[COLOR_OF_ROW_BACKGROUND_ACTIVE_ID] as Int

            pref[intPreferencesKey(COLOR_OF_HIGHLIGHT_ID)] = default[COLOR_OF_HIGHLIGHT_ID] as Int
            pref[intPreferencesKey(FONT_SIZE_OF_HIGHLIGHT_ID)] = default[FONT_SIZE_OF_HIGHLIGHT_ID] as Int
            pref[stringPreferencesKey(FONT_FAMILY_OF_HIGHLIGHT_ID)] = default[FONT_FAMILY_OF_HIGHLIGHT_ID] as String
            pref[intPreferencesKey(COLOR_OF_HIGHLIGHT_BACKGROUND_ID)] = default[COLOR_OF_HIGHLIGHT_BACKGROUND_ID] as Int
            pref[booleanPreferencesKey(TEXT_DECORATION_OF_HIGHLIGHT_ID)] = default[TEXT_DECORATION_OF_HIGHLIGHT_ID] as Boolean


        }
    }

    val default = mapOf(
        COLOR_OF_PRODUCT_ID to Dollar.toArgb(),
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
        COLOR_OF_PROVIDER_BACKGROUND_ID to Milk.toArgb(),
        COLOR_OF_ROW_BACKGROUND_ID to DarkGrey.toArgb(),
        COLOR_OF_ROW_BACKGROUND_ACTIVE_ID to DarkestGrey.toArgb(),

        COLOR_OF_HIGHLIGHT_ID to ColorRed.toArgb(),
        FONT_SIZE_OF_HIGHLIGHT_ID to 14,
        FONT_FAMILY_OF_HIGHLIGHT_ID to Font.JET_BRAIN,
        COLOR_OF_HIGHLIGHT_BACKGROUND_ID to Color.White.toArgb(),
        TEXT_DECORATION_OF_HIGHLIGHT_ID to false



    )

}

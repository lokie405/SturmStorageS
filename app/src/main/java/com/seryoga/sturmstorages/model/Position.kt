package com.seryoga.sturmstorages.model

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.seryoga.sturmstorages.R

//  ---position
enum class Position {

    TOP,
    MIDDLE,
    BOTTOM,
}

//  ---theme type
object ThemeS{

    val DARK = true
    val LIGHT = false
    const val PREFERENCE_KEY = "theme_type"

    fun getName(current: Boolean): Int{
        val list = listOf(
            R.string.setting_theme_dark,
            R.string.setting_theme_light
        )
        return if(current) list[0] else list [1]
    }

    fun getIcon(current: Boolean): Int{
        val list = listOf(
            R.drawable.setting_moon_icon,
            R.drawable.setting_sun_icon
        )
        return if(current) list[0] else list [1]
    }
}

//  ---display type
object DisplayType {

    val ALL_IN_ROW = 0
    val ALL_IN_ROW_AT_CELL = 1
    val PROVIDER_HEADER = 2
    const val PREFERENCE_KEY = "display_type"

    fun getName(current: Int): Int {
        val listOfNames = listOf(
            R.string.setting_display_type_all_in_row,
            R.string.setting_display_type_all_in_row_at_cell,
            R.string.setting_display_type_provider_header
        )
        return listOfNames[current]
    }

    fun next(current: Int): Int {
        if (current < 2) return current + 1
        else return ALL_IN_ROW
    }
}

//  ---show hryvnia sign
object HryvniaSign{

    val HIDE_HRYVNA_SIGN = false
    val SHOW_HRYVNA_SIGN = true
    const val PREFERENCE_KEY = "hryvnia_sign"

    fun getName(current: Boolean): Int{
        val list = listOf(
            R.string.setting_hide_hryvnia_sign,
            R.string.setting_show_hryvnia_sign
        )
        return if(!current) list[0] else list[1]
    }
}

//  ---font style
object FontStyle{

    val BOLD = false
    val THIN = true
//    const val PREFERENCE_KEY = "hryvnia_sign"

//    fun getName(current: Boolean): Int{
//        val list = listOf(
//            R.string.setting_hide_hryvnia_sign,
//            R.string.setting_show_hryvnia_sign
//        )
//        return if(!current) list[0] else list[1]
//    }
}

//  ---color of product
object DesignS{
    const val COLOR_OF_PRODUCT_ID = "color_of_product"
    val COLOR_OF_PRODUCT_PREFERENCES_KEY = intPreferencesKey(COLOR_OF_PRODUCT_ID)

    const val FONT_SIZE_OF_PRODUCT_ID = "font_size_of_product"
    val FONT_SIZE_OF_PRODUCT_PREFERENCES_KEY = intPreferencesKey(FONT_SIZE_OF_PRODUCT_ID)

    const val FONT_FAMILY_OF_PRODUCT_ID = "font_family_of_product"
    val FONT_FAMILY_OF_PRODUCT_PREFERENCES_KEY = stringPreferencesKey(FONT_FAMILY_OF_PRODUCT_ID)

    const val FONT_STYLE_OF_PRODUCT_ID = "font_style_of_product"
    val FONT_STYLE_OF_PRODUCT_PREFERENCES_KEY = booleanPreferencesKey(FONT_STYLE_OF_PRODUCT_ID)


    const val COLOR_OF_PRICE_ID = "color_of_price"
    val COLOR_OF_PRICE_PREFERENCES_KEY = intPreferencesKey(COLOR_OF_PRICE_ID)

    const val FONT_SIZE_OF_PRICE_ID = "font_size_of_price"
    val FONT_SIZE_OF_PRICE_PREFERENCES_KEY = intPreferencesKey(FONT_SIZE_OF_PRICE_ID)

    const val FONT_FAMILY_OF_PRICE_ID = "font_family_of_price"
    val FONT_FAMILY_OF_PRICE_PREFERENCES_KEY = stringPreferencesKey(FONT_FAMILY_OF_PRICE_ID)

    const val FONT_STYLE_OF_PRICE_ID = "font_style_of_price"
    val FONT_STYLE_OF_PRICE_PREFERENCES_KEY = booleanPreferencesKey(FONT_STYLE_OF_PRICE_ID)


    const val COLOR_OF_QUANTITY_ID = "color_of_quantity"
    val COLOR_OF_QUANTITY_PREFERENCES_KEY = intPreferencesKey(COLOR_OF_QUANTITY_ID)

    const val FONT_SIZE_OF_QUANTITY_ID = "font_size_of_quantity"
    val FONT_SIZE_OF_QUANTITY_PREFERENCES_KEY = intPreferencesKey(FONT_SIZE_OF_QUANTITY_ID)

    const val FONT_FAMILY_OF_QUANTITY_ID = "font_family_of_quantity"
    val FONT_FAMILY_OF_QUANTITY_PREFERENCES_KEY = stringPreferencesKey(FONT_FAMILY_OF_QUANTITY_ID)

    const val FONT_STYLE_OF_QUANTITY_ID = "font_style_of_quantity"
    val FONT_STYLE_OF_QUANTITY_PREFERENCES_KEY = booleanPreferencesKey(FONT_STYLE_OF_QUANTITY_ID)


    const val COLOR_OF_PROVIDER_ID = "color_of_provider"
    val COLOR_OF_PROVIDER_PREFERENCES_KEY = intPreferencesKey(COLOR_OF_PROVIDER_ID)

    const val FONT_SIZE_OF_PROVIDER_ID = "font_size_of_provider"
    val FONT_SIZE_OF_PROVIDER_PREFERENCES_KEY = intPreferencesKey(FONT_SIZE_OF_PROVIDER_ID)

    const val FONT_FAMILY_OF_PROVIDER_ID = "font_family_of_provider"
    val FONT_FAMILY_OF_PROVIDER_PREFERENCES_KEY = stringPreferencesKey(FONT_FAMILY_OF_PROVIDER_ID)

    const val FONT_STYLE_OF_PROVIDER_ID = "font_style_of_provider"
    val FONT_STYLE_OF_PROVIDER_PREFERENCES_KEY = booleanPreferencesKey(FONT_STYLE_OF_PROVIDER_ID)


    const val COLOR_OF_PROVIDER_SECOND_ID = "color_of_provider_second"
    val COLOR_OF_PROVIDER_SECOND_PREFERENCES_KEY = intPreferencesKey(COLOR_OF_PROVIDER_SECOND_ID)

    const val COLOR_OF_PROVIDER_BACKGROUND_ID = "color_of_provider_background"
    val COLOR_OF_PROVIDER_BACKGROUND_PREFERENCES_KEY = intPreferencesKey(COLOR_OF_PROVIDER_BACKGROUND_ID)

    const val COLOR_OF_ROW_BACKGROUND_ID = "color_of_row_background"
    val COLOR_OF_ROW_BACKGROUND_PREFERENCES_KEY = intPreferencesKey(COLOR_OF_ROW_BACKGROUND_ID)

    const val COLOR_OF_ROW_BACKGROUND_ACTIVE_ID = "color_of_row_background_active"
    val COLOR_OF_ROW_BACKGROUND_ACTIVE_PREFERENCES_KEY = intPreferencesKey(COLOR_OF_ROW_BACKGROUND_ACTIVE_ID)

}

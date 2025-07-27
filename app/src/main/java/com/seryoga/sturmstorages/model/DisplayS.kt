package com.seryoga.sturmstorages.model

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import com.seryoga.sturmstorages.R

object DisplayS {

//    ---theme
    const val THEME_ID = "theme_type"
    val THEME_PREFERENCE_KEY = booleanPreferencesKey(THEME_ID)

    fun getNameTheme(current: Boolean): Int{
        val list = listOf(
            R.string.setting_theme_dark,
            R.string.setting_theme_light
        )
        return if(current) list[0] else list [1]
    }

    fun getIconTheme(current: Boolean): Int{
        val list = listOf(
            R.drawable.setting_moon_icon,
            R.drawable.setting_sun_icon
        )
        return if(current) list[0] else list [1]
    }

//    ---display
    const val DISPLAY_ID = "display_type"
    val DISPLAY_PREFERENCE_KEY = intPreferencesKey(DISPLAY_ID)


    fun getNameDisplay(current: Int): Int {
        val listOfNames = listOf(
            R.string.setting_display_type_all_in_row,
            R.string.setting_display_type_all_in_row_at_cell,
            R.string.setting_display_type_provider_header
        )
        return listOfNames[current]
    }

    fun nextDisplayType(current: Int): Int {
        if (current < 2) return current + 1
        else return DisplayType.ALL_IN_ROW
    }

    const val HRYVNIA_SIGN_ID = "hryvnia_sign"
    val HRYVNIA_SIGN_PREFERENCE_KEY = booleanPreferencesKey(HRYVNIA_SIGN_ID)
//    val HIDE_HRYVNA_SIGN = false
//    val SHOW_HRYVNA_SIGN = true

    fun getNameHryvniaSign(current: Boolean): Int{
        val list = listOf(
            R.string.setting_hide_hryvnia_sign,
            R.string.setting_show_hryvnia_sign
        )
        return if(!current) list[0] else list[1]
    }


    const val PROVIDER_LIST_TYPE_ID = "provider_list_type"
    val PROVIDER_LIST_TYPE_PREFERENCES_KEY = booleanPreferencesKey(PROVIDER_LIST_TYPE_ID)

    fun getNameProviderListType(current: Boolean): Int{
        return if(current){
            R.string.setting_provider_list_type_list
        } else R.string.setting_provider_list_type_tile
    }

    fun getIconProviderListType(current: Boolean): Int{
        return if(current){
            R.drawable.list_display_icon
        } else R.drawable.tile_display_icon
    }

    fun toggleProviderListType(current: Boolean): Boolean {
        return !current
    }

    val default = mapOf(
        THEME_ID to true,
        DISPLAY_ID to DisplayType.ALL_IN_ROW,
        HRYVNIA_SIGN_ID to true,
        PROVIDER_LIST_TYPE_ID to true,
    )

}
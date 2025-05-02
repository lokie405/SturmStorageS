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

//  ---toggle i sensitive
object ISensitive{
    val SENSITIVE = true
    fun getName(current: Boolean): Int{
        val list = listOf(
            R.string.setting_i_sensitive,
            R.string.setting_i_insensitive,
        )
        return if(!current) list[0] else list[1]
    }
}




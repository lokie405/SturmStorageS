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
//object ThemeS{
//    val DARK = true
//    val LIGHT = false
//    const val PREFERENCE_KEY = "theme_type"
//
//    fun getName(current: Boolean): Int{
//        val list = listOf(
//            R.string.setting_theme_dark,
//            R.string.setting_theme_light
//        )
//        return if(current) list[0] else list [1]
//    }
//
//    fun getIcon(current: Boolean): Int{
//        val list = listOf(
//            R.drawable.setting_moon_icon,
//            R.drawable.setting_sun_icon
//        )
//        return if(current) list[0] else list [1]
//    }
//}

//  ---display type
//object DisplayType {
//
//
//}

//  ---show hryvnia sign
object HryvniaSign{

}

//  ---toggle i sensitive
object ISensitive{
    val SENSITIVE = true

}




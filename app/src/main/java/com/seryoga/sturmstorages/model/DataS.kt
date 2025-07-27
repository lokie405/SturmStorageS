package com.seryoga.sturmstorages.model

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.seryoga.sturmstorages.R

object DataS {

    const val URL_ID = "url_to_scrap"
    val URL_PREFERENCE_KEY = stringPreferencesKey(URL_ID)

    const val AUTOUPDATE_ID = "autoupdate"
    val AUTOUPDATE_PREFERENCE_KEY = booleanPreferencesKey(AUTOUPDATE_ID)

    fun getNameIsAutoupdate(current: Boolean): Int {
        return if(current){
            R.string.setting_autoupdate_allow
        } else R.string.setting_autoupdate_deny
    }

    fun getIconIsAutoupdate(current: Boolean): Int{
        return if(current){
            R.drawable.setting_autoupdate_allow
        } else R.drawable.cloud_error_icon
    }

    fun toggleAutoupdateType(current: Boolean): Boolean {
        return !current
    }




//    const val UA_TO_EN_KEYBOARD_ID = "transliteration"
//    val UA_TO_EN_KEYBOARD_KEY = booleanPreferencesKey(UA_TO_EN_KEYBOARD_ID)
//
//    fun getNameUaToEnKeyboard (current: Boolean): Int{
//        val list = listOf(
//            R.string.setting_i_sensitive,
//            R.string.setting_i_insensitive,
//        )
//        return if(!current) list[0] else list[1]
//    }
//
//    val ukToEnKeyboardMap:  Map<Char, Char> = mapOf(
//        /*uk*/'А' to /*en*/'A',
//        /*uk*/'а' to /*en*/'a',
//        /*uk*/'В' to /*en*/'B',
//        /*uk*/'С' to /*en*/'C',
//        /*uk*/'с' to /*en*/'c',
//        /*uk*/'Е' to /*en*/'E',
//        /*uk*/'е' to /*en*/'e',
//        /*uk*/'Н' to /*en*/'H',
//        /*uk*/'І' to /*en*/'I',
//        /*uk*/'і' to /*en*/'i',
//        /*uk*/'К' to /*en*/'K',
//        /*uk*/'к' to /*en*/'k',
//        /*uk*/'М' to /*en*/'M',
//        /*uk*/'О' to /*en*/'O',
//        /*uk*/'о' to /*en*/'o',
//        /*uk*/'Р' to /*en*/'P',
//        /*uk*/'р' to /*en*/'p',
//        /*uk*/'Т' to /*en*/'T',
//        /*uk*/'Т' to /*en*/'T',
//        /*uk*/'Х' to /*en*/'X',
//        /*uk*/'х' to /*en*/'x',
//        /*uk*/'у' to /*en*/'y',
//    )
//    var reversUkToEnKeyboardMap:  Map<Char, Char> = ukToEnKeyboardMap.entries.associate {
//        (k, v) -> v to k
//    }

    val default = mapOf(
        URL_ID to "https://script.google.com/macros/s/AKfycby4MLgVrwZEHGc0ELdTX7Sxu_l7zNiHEJyox4EF1x_zLSp2bicV2JFD42gDiUfN24Q/exec",
//        URL_ID to "https://script.google.com/macros/s/AKfycby4MLgVrwZEHGc0ELdTX7Sxu_l7zNiHEJyox4EF1x_zLSp2bicV2JFD42gDiUfN24Q/exec",
//        URL_ID to "https://script.google.com/macros/s/AKfycby4MLgVrwEHGc02ELdTX7Sxu_l7zNiHEJyox4EF1x_zLSp2bicV2JFD42gDiUfN24Q/exec",
        AUTOUPDATE_ID to true ,

//        I_SENSITIVE_ID to true as Boolean  //  true for i - uk, i - gb; false for i-uk/gb


    )
}
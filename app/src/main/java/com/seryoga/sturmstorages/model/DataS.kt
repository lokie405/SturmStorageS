package com.seryoga.sturmstorages.model

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.seryoga.sturmstorages.R

object DataS {

    const val URL_ID = "url_to_scrap"
    val URL_PREFERENCE_KEY = stringPreferencesKey(URL_ID)

    const val I_SENSITIVE_ID = "i_sensitive"
    val I_SENSITIVE_KEY = booleanPreferencesKey(I_SENSITIVE_ID)
    fun getNameISensitive(current: Boolean): Int{
        val list = listOf(
            R.string.setting_i_sensitive,
            R.string.setting_i_insensitive,
        )
        return if(!current) list[0] else list[1]
    }

    val default = mapOf(
        URL_ID to "https://script.google.com/macros/s/AKfycby4MLgVrwZEHGc0ELdTX7Sxu_l7zNiHEJyox4EF1x_zLSp2bicV2JFD42gDiUfN24Q/exec" as String,
        I_SENSITIVE_ID to true as Boolean  //  true for i - ua, i - gb; false for i-ua/gb


    )
}
package com.seryoga.sturmstorages.model

import android.annotation.SuppressLint
import android.util.Log
import com.seryoga.sturmstorages.R
import com.seryoga.sturmstorages.ui.theme.MilkGrey
import com.seryoga.sturmstorages.ui.theme.DarkestGrey
import com.seryoga.sturmstorages.ui.theme.DarkGrey
import com.seryoga.sturmstorages.util.Const.TAG

object Theme {
    const val DARK = 0
    const val LIGHT = 1
    private val iconsList = listOf(R.drawable.setting_sun_icon, R.drawable.setting_moon_icon)
    private var _current = DARK

    @SuppressLint("SuspiciousIndentation")
    fun setCurrent(current: Int){
        _current = current
//            Log.i(TAG, "setCurrent $current");
    }

    fun getCurrentIcon(): Int{
        return iconsList.get(_current)
//            Log.i(TAG, "getCurrent $_current");
    }
    fun togle(): Int{
//            Log.i(TAG, "size ${iconsList.size}");
//            Log.i(TAG, "curPre ${_current}");


        if(_current == iconsList.size - 1) setCurrent(DARK)
        else setCurrent(++_current)
//            Log.i(TAG, "curAfter ${_current}")
//            Log.i(TAG, "togle $_current")

        return _current
    }

    object Colors{
        var mainBackgroundColor = DarkestGrey
        var secondBackgroundColor = DarkGrey
        var mainFontColor = MilkGrey
    }


}
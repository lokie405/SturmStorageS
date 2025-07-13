package com.seryoga.sturmstorages.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.seryoga.sturmstorages.db.Product
import com.seryoga.sturmstorages.ui.theme.ColorBlue
import com.seryoga.sturmstorages.ui.theme.ColorMagenta

object Const {
//    const val MAIN_SCREEN = 1
//    const val SETTING_SCREEN = 2

    const val TABLE_PRODUCTS_NAME = "table_products"
    const val TABLE_PRODUCTS_OLD_NAME = "table_products_old"
    const val TABLE_PRODUCTS_NEW_NAME = "table_products_new"
    const val TABLE_PROVIDER_NAME = "table_providers"
    const val SETTING_DATA_STORE = "setting"
    const val NULL_DATE_PATTERN = "00.00.00 00:00:00"

    //    var URL = "https://script.google.com/macros/s/AKfycby4MLgVrwZEHGc0ELdTX7Sxu_l7zNiHEJyox4EF1x_zLSp2bicV2JFD42gDiUfN24Q/exec"
    val TAG = "MyLog"

    //    val paddingValues: PaddingValues = PaddingValues(start = 0.dp, top = 56.dp, end = 0.dp, bottom = 70.dp)
    val TOP_BAR_HEIGHT = 56.dp
    val BOTTOM_BAR_HEIGHT = 70.dp

    //    ---setting
    val testItem: Product = Product(
        id = 3333,
        name = "CC9940CL Пилка ланцюгова акумуляторна 16\" 40В STURM, арт. 18614 (шт.)",
        price = "5'648.00 грн.",
        quantity = "12.000",
        provider = "УЗП - Електроiнструмент",
        date = "00/00"
    )


    var COLOR_PROVIDER_1 = ColorBlue
    var COLOR_PROVIDER_2 = ColorMagenta
    var COLOR_PROVIDER_MAIN = Color.White
}



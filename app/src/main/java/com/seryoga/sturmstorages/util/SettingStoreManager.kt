import android.content.Context
import android.util.Log
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.seryoga.sturmstorages.model.DesignS
import com.seryoga.sturmstorages.model.DisplayType
import com.seryoga.sturmstorages.model.HryvniaSign
import com.seryoga.sturmstorages.model.SettingData
import com.seryoga.sturmstorages.model.ThemeS
import com.seryoga.sturmstorages.ui.theme.Cardboard
import com.seryoga.sturmstorages.ui.theme.ColorBlue
import com.seryoga.sturmstorages.ui.theme.ColorGrey
import com.seryoga.sturmstorages.ui.theme.ColorMagenta
import com.seryoga.sturmstorages.ui.theme.DarkestGrey
import com.seryoga.sturmstorages.ui.theme.Dollar
import com.seryoga.sturmstorages.ui.theme.Milk
import com.seryoga.sturmstorages.ui.theme.MilkGrey
import com.seryoga.sturmstorages.ui.theme.Silver
import com.seryoga.sturmstorages.util.Const
import com.seryoga.sturmstorages.util.Const.TAG
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlin.math.log


//val Context.settingStore: DataStore<Preferences> by preferencesDataStore(Const.SETTING_DATA_STORE)
val Context.settingStore: DataStore<Preferences> by preferencesDataStore(Const.SETTING_DATA_STORE)

class SettingStoreManager(val context: Context) {

    private val THEME_TYPE = booleanPreferencesKey(ThemeS.PREFERENCE_KEY)
    private val DISPLAY_TYPE = intPreferencesKey(DisplayType.PREFERENCE_KEY)
    private val HRYVNIA_SIGN = booleanPreferencesKey(HryvniaSign.PREFERENCE_KEY)


    suspend fun toggleAndSaveThemeType() {
        val isDark = this.getThemeType().first()
        context.settingStore.edit { pref ->
            pref[THEME_TYPE] = !isDark
        }
    }

    suspend fun toggleAndSaveDisplayType() {
        val displayType = this.getDisplayType().first()
        context.settingStore.edit { pref ->
            pref[DISPLAY_TYPE] = DisplayType.next(displayType)
        }
    }

    suspend fun toggleAndSaveHryvniaSign() {
        val hryvniaSign = this.getHryvniaSign().first()
        context.settingStore.edit { pref ->
            pref[HRYVNIA_SIGN] = !hryvniaSign
        }
    }


    suspend fun saveColor(element: String, colorInt: Int) {
        context.settingStore.edit { pref ->
            pref[intPreferencesKey(DesignS.map[element]?.get(0).toString())] = colorInt
        }
    }

    suspend fun saveFontSize(element: String, fontSize: Int) {
        context.settingStore.edit { pref ->
            pref[intPreferencesKey(DesignS.map[element]?.get(1).toString())] = fontSize
        }
    }

//    suspend fun saveBackgroundColorOfProvider(colorInt: Int){
//        context.settingStore.edit { pref ->
//            pref[intPreferencesKey(DesignS.COLOR_OF_PROVIDER_BACKGROUND_ID)] = colorInt
//
//        }
//    }

    suspend fun saveFontFamily(element: String, fontFamily: String){
        Log.i(TAG, "SAVEFONTSIZE: element ${element}, fontsize: ${fontFamily}");
        Log.i(TAG, "SAVEFONTSIZE2: element ${DesignS.map[element]?.get(2).toString()}");
        context.settingStore.edit { pref ->
            pref[stringPreferencesKey(DesignS.map[element]?.get(2).toString())] = fontFamily
        }
    }

//    suspend fun toggleAndSaveFontStyle(element: String){
//        val style = this.getFontStyle(element).first()
//        Log.i(TAG, "fontstyle: ${style}");
//        context.settingStore.edit { pref ->
//            pref[booleanPreferencesKey(DesignS.map[element]?.get(3).toString())] = !style
//        }
//    }


    val settingsFlow: Flow<SettingData> = context.settingStore.data.map { pref ->
        SettingData(
            themeType = pref[THEME_TYPE] ?: ThemeS.DARK,
            displayType = pref[DISPLAY_TYPE] ?: DisplayType.ALL_IN_ROW,
            hryvniaSign = pref[HRYVNIA_SIGN] ?: HryvniaSign.HIDE_HRYVNA_SIGN,

            colorOfProduct = pref[DesignS.COLOR_OF_PRODUCT_PREFERENCES_KEY] ?: Milk.toArgb(),
            fontSizeOfProduct = pref[DesignS.FONT_SIZE_OF_PRODUCT_PREFERENCES_KEY] ?: 12,
            fontFamilyOfProduct = pref[DesignS.FONT_FAMILY_OF_PRODUCT_PREFERENCES_KEY] ?: "",
//            fontStyleOfProduct = pref[DesignS.FONT_STYLE_OF_PRODUCT_PREFERENCES_KEY] ?: FontStyle.THIN,

            colorOfPrice = pref[DesignS.COLOR_OF_PRICE_PREFERENCES_KEY] ?: Dollar.toArgb(),
            fontSizeOfPrice = pref[DesignS.FONT_SIZE_OF_PRICE_PREFERENCES_KEY] ?: 12,
            fontFamilyOfPrice = pref[DesignS.FONT_FAMILY_OF_PRICE_PREFERENCES_KEY] ?: "",
//            fontStyleOfPrice = pref[DesignS.FONT_STYLE_OF_PRICE_PREFERENCES_KEY] ?: FontStyle.THIN,

            colorOfQuantity = pref[DesignS.COLOR_OF_QUANTITY_PREFERENCES_KEY] ?: Cardboard.toArgb(),
            fontSizeOfQuantity = pref[DesignS.FONT_SIZE_OF_QUANTITY_PREFERENCES_KEY] ?: 12,
            fontFamilyOfQuantity = pref[DesignS.FONT_FAMILY_OF_QUANTITY_PREFERENCES_KEY] ?: "",
//            fontStyleOfQuantity = pref[DesignS.FONT_STYLE_OF_QUANTITY_PREFERENCES_KEY] ?: FontStyle.THIN,

            colorOfProvider = pref[DesignS.COLOR_OF_PROVIDER_PREFERENCES_KEY] ?: ColorMagenta.toArgb(),
            fontSizeOfProvider = pref[DesignS.FONT_SIZE_OF_PROVIDER_PREFERENCES_KEY] ?: 12,
            fontFamilyOfProvider = pref[DesignS.FONT_FAMILY_OF_PROVIDER_PREFERENCES_KEY] ?: "",
//            fontStyleOfProvider = pref[DesignS.FONT_STYLE_OF_PROVIDER_PREFERENCES_KEY] ?: FontStyle.THIN,

            colorOfProviderSecond = pref[DesignS.COLOR_OF_PROVIDER_SECOND_PREFERENCES_KEY] ?: ColorBlue.toArgb(),
            colorOfProviderBackground = pref[DesignS.COLOR_OF_PROVIDER_BACKGROUND_PREFERENCES_KEY] ?: Silver.toArgb(),
            colorOfRowBackground = pref[DesignS.COLOR_OF_ROW_BACKGROUND_PREFERENCES_KEY] ?: DarkestGrey.toArgb(),
            colorOfRowBackgroundActive = pref[DesignS.COLOR_OF_ROW_BACKGROUND_ACTIVE_PREFERENCES_KEY] ?: ColorGrey.toArgb(),
        )
    }
//    fun getSetting(context: Context) = context.settingStore.data.map { pref ->
//        return@map SettingData(pref[DISPLAY_TYPE] ?: Const.DISPLAY_TYPE_ALL_IN_ROW)
//    }


    fun getThemeType(): Flow<Boolean> =
        context.settingStore.data.map { it[THEME_TYPE] ?: false }

    fun getDisplayType(): Flow<Int> =
        context.settingStore.data.map { it[DISPLAY_TYPE] ?: DisplayType.ALL_IN_ROW }

    fun getHryvniaSign(): Flow<Boolean> =
        context.settingStore.data.map { it[HRYVNIA_SIGN] ?: HryvniaSign.HIDE_HRYVNA_SIGN }

//    fun getFontFamily(): Flow<String> =
//        context.settingStore.data.map { it[] }

//    fun getFontStyle(element: String): Flow<Boolean> =
//        context.settingStore.data.map { it[booleanPreferencesKey(DesignS.map[element]?.get(3).toString())] ?: false }

//    fun getColorOfProduct(): Flow<Int> =
//        context.settingStore.data.map { it[ColorS.COLOR_OF_PRODUCT_PREFERENCES_KEY] ?: Color.Cyan.toArgb() }
}


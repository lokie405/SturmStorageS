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
import com.seryoga.sturmstorages.model.DataS
import com.seryoga.sturmstorages.model.DesignS
import com.seryoga.sturmstorages.model.DisplayS
import com.seryoga.sturmstorages.model.DisplayType
import com.seryoga.sturmstorages.model.HryvniaSign
import com.seryoga.sturmstorages.model.ISensitive
import com.seryoga.sturmstorages.model.SettingData
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







    suspend fun saveURL(url: String){
        context.settingStore.edit { pref ->
            pref[DataS.URL_PREFERENCE_KEY] = url
        }
//        Log.i(TAG, "0f0f0f0f   ${getURL().first()}")
    }

    suspend fun toggleAndSaveISensitive() {
        val isSensitive = this.getISensitive().first()
        context.settingStore.edit { pref ->
            pref[DataS.I_SENSITIVE_KEY] = !isSensitive
        }
    }


    suspend fun toggleAndSaveThemeType() {
        val isDark = this.getThemeType().first()
        context.settingStore.edit { pref ->
            pref[DisplayS.THEME_PREFERENCE_KEY] = !isDark
        }
    }

    suspend fun toggleAndSaveDisplayType() {
        val displayType = this.getDisplayType().first()
        context.settingStore.edit { pref ->
            pref[DisplayS.DISPLAY_PREFERENCE_KEY] = DisplayS.nextDisplayType(displayType)
        }
    }

    suspend fun toggleAndSaveHryvniaSign() {
        val hryvniaSign = this.getHryvniaSign().first()
        context.settingStore.edit { pref ->
            pref[DisplayS.HRYVNIA_SIGN_PREFERENCE_KEY] = !hryvniaSign
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
//        Log.i(TAG, "SAVEFONTSIZE: element ${element}, fontsize: ${fontFamily}");
//        Log.i(TAG, "SAVEFONTSIZE2: element ${DesignS.map[element]?.get(2).toString()}");
        context.settingStore.edit { pref ->
            pref[stringPreferencesKey(DesignS.map[element]?.get(2).toString())] = fontFamily
        }
    }

//    suspend fun resetDesignToDefault(){
//        context.settingStore.edit { pref ->
//            pref[intPreferencesKey(DesignS.COLOR_OF_PRODUCT_ID)] = DesignS.default[DesignS.COLOR_OF_PRODUCT_ID] as Int
//            pref[intPreferencesKey(DesignS.FONT_SIZE_OF_PRODUCT_ID)] = DesignS.default[DesignS.FONT_SIZE_OF_PRODUCT_ID] as Int
//            pref[stringPreferencesKey(DesignS.FONT_FAMILY_OF_PRODUCT_ID)] = DesignS.default[DesignS.FONT_FAMILY_OF_PRODUCT_ID] as String
//
//            pref[intPreferencesKey(DesignS.COLOR_OF_PRICE_ID)] = DesignS.default[DesignS.COLOR_OF_PRICE_ID] as Int
//            pref[intPreferencesKey(DesignS.FONT_SIZE_OF_PRICE_ID)] = DesignS.default[DesignS.FONT_SIZE_OF_PRICE_ID] as Int
//            pref[stringPreferencesKey(DesignS.FONT_FAMILY_OF_PRICE_ID)] = DesignS.default[DesignS.FONT_FAMILY_OF_PRICE_ID] as String
//
//            pref[intPreferencesKey(DesignS.COLOR_OF_QUANTITY_ID)] = DesignS.default[DesignS.COLOR_OF_QUANTITY_ID] as Int
//            pref[intPreferencesKey(DesignS.FONT_SIZE_OF_QUANTITY_ID)] = DesignS.default[DesignS.FONT_SIZE_OF_QUANTITY_ID] as Int
//            pref[stringPreferencesKey(DesignS.FONT_FAMILY_OF_QUANTITY_ID)] = DesignS.default[DesignS.FONT_FAMILY_OF_QUANTITY_ID] as String
//
//            pref[intPreferencesKey(DesignS.COLOR_OF_PROVIDER_ID)] = DesignS.default[DesignS.COLOR_OF_PROVIDER_ID] as Int
//            pref[intPreferencesKey(DesignS.FONT_SIZE_OF_PROVIDER_ID)] = DesignS.default[DesignS.FONT_SIZE_OF_PROVIDER_ID] as Int
//            pref[stringPreferencesKey(DesignS.FONT_FAMILY_OF_PROVIDER_ID)] = DesignS.default[DesignS.FONT_FAMILY_OF_PROVIDER_ID] as String
//            pref[intPreferencesKey(DesignS.COLOR_OF_PROVIDER_SECOND_ID)] = DesignS.default[DesignS.COLOR_OF_PROVIDER_SECOND_ID] as Int
//            pref[intPreferencesKey(DesignS.COLOR_OF_PROVIDER_BACKGROUND_ID)] = DesignS.default[DesignS.COLOR_OF_PROVIDER_BACKGROUND_ID] as Int
//            pref[intPreferencesKey(DesignS.COLOR_OF_ROW_BACKGROUND_ID)] = DesignS.default[DesignS.COLOR_OF_ROW_BACKGROUND_ID] as Int
//            pref[intPreferencesKey(DesignS.COLOR_OF_ROW_BACKGROUND_ACTIVE_ID)] = DesignS.default[DesignS.COLOR_OF_ROW_BACKGROUND_ACTIVE_ID] as Int
//        }
//    }

    val settingsFlow: Flow<SettingData> = context.settingStore.data.map { pref ->
        SettingData(
            url = pref[DataS.URL_PREFERENCE_KEY] ?: DataS.default[DataS.URL_ID] as String,
            iSensitive = pref[DataS.I_SENSITIVE_KEY] ?: DataS.default[DataS.I_SENSITIVE_ID] as Boolean,

            themeType = pref[DisplayS.THEME_PREFERENCE_KEY] ?: DisplayS.default[DisplayS.THEME_ID] as Boolean,
            displayType = pref[DisplayS.DISPLAY_PREFERENCE_KEY] ?: DisplayS.default[DisplayS.DISPLAY_ID] as Int,
            hryvniaSign = pref[DisplayS.HRYVNIA_SIGN_PREFERENCE_KEY] ?: DisplayS.default[DisplayS.HRYVNIA_SIGN_ID] as Boolean,

            colorOfProduct = pref[DesignS.COLOR_OF_PRODUCT_PREFERENCES_KEY] ?: DesignS.default[DesignS.COLOR_OF_PRODUCT_ID] as Int,
            fontSizeOfProduct = pref[DesignS.FONT_SIZE_OF_PRODUCT_PREFERENCES_KEY] ?: DesignS.default[DesignS.FONT_SIZE_OF_PRODUCT_ID] as Int,
            fontFamilyOfProduct = pref[DesignS.FONT_FAMILY_OF_PRODUCT_PREFERENCES_KEY] ?: DesignS.default[DesignS.FONT_FAMILY_OF_PRODUCT_ID] as String,
//            fontStyleOfProduct = pref[DesignS.FONT_STYLE_OF_PRODUCT_PREFERENCES_KEY] ?: FontStyle.THIN,

            colorOfPrice = pref[DesignS.COLOR_OF_PRICE_PREFERENCES_KEY] ?: DesignS.default[DesignS.COLOR_OF_PRICE_ID] as Int,
            fontSizeOfPrice = pref[DesignS.FONT_SIZE_OF_PRICE_PREFERENCES_KEY] ?: DesignS.default[DesignS.FONT_SIZE_OF_PRICE_ID] as Int,
            fontFamilyOfPrice = pref[DesignS.FONT_FAMILY_OF_PRICE_PREFERENCES_KEY] ?: DesignS.default[DesignS.FONT_FAMILY_OF_PRICE_ID] as String,
//            fontStyleOfPrice = pref[DesignS.FONT_STYLE_OF_PRICE_PREFERENCES_KEY] ?: FontStyle.THIN,

            colorOfQuantity = pref[DesignS.COLOR_OF_QUANTITY_PREFERENCES_KEY] ?: DesignS.default[DesignS.COLOR_OF_QUANTITY_ID] as Int,
            fontSizeOfQuantity = pref[DesignS.FONT_SIZE_OF_QUANTITY_PREFERENCES_KEY] ?: DesignS.default[DesignS.FONT_SIZE_OF_QUANTITY_ID] as Int,
            fontFamilyOfQuantity = pref[DesignS.FONT_FAMILY_OF_QUANTITY_PREFERENCES_KEY] ?: DesignS.default[DesignS.FONT_FAMILY_OF_QUANTITY_ID] as String,
//            fontStyleOfQuantity = pref[DesignS.FONT_STYLE_OF_QUANTITY_PREFERENCES_KEY] ?: FontStyle.THIN,

            colorOfProvider = pref[DesignS.COLOR_OF_PROVIDER_PREFERENCES_KEY] ?: DesignS.default[DesignS.COLOR_OF_PROVIDER_ID] as Int,
            fontSizeOfProvider = pref[DesignS.FONT_SIZE_OF_PROVIDER_PREFERENCES_KEY] ?: DesignS.default[DesignS.FONT_SIZE_OF_PROVIDER_ID] as Int,
            fontFamilyOfProvider = pref[DesignS.FONT_FAMILY_OF_PROVIDER_PREFERENCES_KEY] ?: DesignS.default[DesignS.FONT_FAMILY_OF_PROVIDER_ID] as String,
//            fontStyleOfProvider = pref[DesignS.FONT_STYLE_OF_PROVIDER_PREFERENCES_KEY] ?: FontStyle.THIN,

            colorOfProviderSecond = pref[DesignS.COLOR_OF_PROVIDER_SECOND_PREFERENCES_KEY] ?: DesignS.default[DesignS.COLOR_OF_PROVIDER_SECOND_ID] as Int,
            colorOfProviderBackground = pref[DesignS.COLOR_OF_PROVIDER_BACKGROUND_PREFERENCES_KEY] ?: DesignS.default[DesignS.COLOR_OF_PROVIDER_BACKGROUND_ID] as Int,
            colorOfRowBackground = pref[DesignS.COLOR_OF_ROW_BACKGROUND_PREFERENCES_KEY] ?: DesignS.default[DesignS.COLOR_OF_ROW_BACKGROUND_ID] as Int,
            colorOfRowBackgroundActive = pref[DesignS.COLOR_OF_ROW_BACKGROUND_ACTIVE_PREFERENCES_KEY] ?: DesignS.default[DesignS.COLOR_OF_ROW_BACKGROUND_ACTIVE_ID] as Int,
        )
    }
//    fun getSetting(context: Context) = context.settingStore.data.map { pref ->
//        return@map SettingData(pref[DISPLAY_TYPE] ?: Const.DISPLAY_TYPE_ALL_IN_ROW)
//    }

    fun getISensitive(): Flow<Boolean> =
        context.settingStore.data.map { it[DataS.I_SENSITIVE_KEY] ?: DataS.default[DataS.I_SENSITIVE_ID] as Boolean }

    fun getURL(): Flow<String> =
        context.settingStore.data.map { it[DataS.URL_PREFERENCE_KEY] ?: DataS.default[DataS.URL_ID] as String}

    fun getThemeType(): Flow<Boolean> =
        context.settingStore.data.map { it[DisplayS.THEME_PREFERENCE_KEY] ?: DisplayS.default[DisplayS.THEME_ID] as Boolean }

    fun getDisplayType(): Flow<Int> =
        context.settingStore.data.map { it[DisplayS.DISPLAY_PREFERENCE_KEY] ?: DisplayS.default[DisplayS.DISPLAY_ID] as Int }

    fun getHryvniaSign(): Flow<Boolean> =
        context.settingStore.data.map { it[DisplayS.HRYVNIA_SIGN_PREFERENCE_KEY] ?: DisplayS.default[DisplayS.HRYVNIA_SIGN_ID] as Boolean}

//    fun getFontFamily(): Flow<String> =
//        context.settingStore.data.map { it[] }

//    fun getFontStyle(element: String): Flow<Boolean> =
//        context.settingStore.data.map { it[booleanPreferencesKey(DesignS.map[element]?.get(3).toString())] ?: false }

//    fun getColorOfProduct(): Flow<Int> =
//        context.settingStore.data.map { it[ColorS.COLOR_OF_PRODUCT_PREFERENCES_KEY] ?: Color.Cyan.toArgb() }
}


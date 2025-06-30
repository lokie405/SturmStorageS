import android.content.Context
import android.util.Log
import androidx.compose.ui.graphics.Color
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
import com.seryoga.sturmstorages.model.SettingData
import com.seryoga.sturmstorages.screen.toHex
import com.seryoga.sturmstorages.util.Const
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


//val Context.settingStore: DataStore<Preferences> by preferencesDataStore(Const.SETTING_DATA_STORE)
val Context.settingStore: DataStore<Preferences> by preferencesDataStore(Const.SETTING_DATA_STORE)

class SettingStoreManager(val context: Context) {


    suspend fun saveURL(url: String){
        context.settingStore.edit { pref ->
            pref[DataS.URL_PREFERENCE_KEY] = url
        }
//        Log.i(TAG, "0f0f0f0f   ${getURL().first()}")
    }

    suspend fun toggleAndSaveIsAutoupdate() {
        val autoupdateType = this.getAutoUpdateType().first()
        context.settingStore.edit { pref ->
            pref[DataS.AUTOUPDATE_PREFERENCE_KEY] = DataS.toggleAutoupdateType(autoupdateType)
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

    suspend fun saveBackgroundColor(element: String, colorInt: Int) {
        context.settingStore.edit { pref ->
            pref[intPreferencesKey(DesignS.map[element]?.get(3).toString())] = colorInt
        }
    }
    suspend fun saveDifferentProviderColor(element: String, colorInt: Int) {
        context.settingStore.edit { pref ->
            pref[intPreferencesKey(DesignS.map[DesignS.PROVIDER_DESIGN]?.get(4).toString())] = colorInt
        }
    }
    suspend fun saveBackgroundActiveColor(element: String, colorInt: Int) {
        context.settingStore.edit { pref ->
            pref[intPreferencesKey(DesignS.map[DesignS.BACKGROUND_DESIGN]?.get(5).toString())] = colorInt
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
        context.settingStore.edit { pref ->
            pref[stringPreferencesKey(DesignS.map[element]?.get(2).toString())] = fontFamily
        }
    }

    suspend fun saveTextDecoration(element: String, isDecoration: Boolean){
        context.settingStore.edit { pref ->
            pref[booleanPreferencesKey(DesignS.map[element]?.get(4).toString())] = isDecoration
        }
    }

    val settingsFlow: Flow<SettingData> = context.settingStore.data.map { pref ->
        SettingData(
            url = pref[DataS.URL_PREFERENCE_KEY] ?: DataS.default[DataS.URL_ID] as String,
            isAutoupdate = pref[DataS.AUTOUPDATE_PREFERENCE_KEY] ?: DataS.default[DataS.AUTOUPDATE_ID] as Boolean,

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

            colorOfHighlight = pref[DesignS.COLOR_OF_HIGHLIGHT_PREFERENCES_KEY] ?: DesignS.default[DesignS.COLOR_OF_HIGHLIGHT_ID] as Int,
            fontSizeOfHighlight = pref[DesignS.FONT_SIZE_OF_HIGHLIGHT_PREFERENCE_KEY] ?: DesignS.default[DesignS.FONT_SIZE_OF_HIGHLIGHT_ID] as Int,
            fontFamilyOfHighlight = pref[DesignS.FONT_FAMILY_OF_HIGHLIGHT_PREFERENCE_KEY] ?: DesignS.default[DesignS.FONT_FAMILY_OF_HIGHLIGHT_ID] as String,
            colorOfHighlightBackground = pref[DesignS.COLOR_OF_HIGHLIGHT_BACKGROUND_PREFERENCE_KEY] ?: DesignS.default[DesignS.COLOR_OF_HIGHLIGHT_BACKGROUND_ID] as Int,
            textDecorationOfHighlight = pref[DesignS.TEXT_DECORATION_OF_HIGHLIGHT_PREFERENCE_KEY] ?: DesignS.default[DesignS.TEXT_DECORATION_OF_HIGHLIGHT_ID] as Boolean,
        )
    }

    fun getAutoUpdateType(): Flow<Boolean> =
        context.settingStore.data.map { it[DataS.AUTOUPDATE_PREFERENCE_KEY] ?: DataS.default[DataS.AUTOUPDATE_ID] as Boolean }

    fun getURL(): Flow<String> =
        context.settingStore.data.map { it[DataS.URL_PREFERENCE_KEY] ?: DataS.default[DataS.URL_ID] as String}

    fun getThemeType(): Flow<Boolean> =
        context.settingStore.data.map { it[DisplayS.THEME_PREFERENCE_KEY] ?: DisplayS.default[DisplayS.THEME_ID] as Boolean }

    fun getDisplayType(): Flow<Int> =
        context.settingStore.data.map { it[DisplayS.DISPLAY_PREFERENCE_KEY] ?: DisplayS.default[DisplayS.DISPLAY_ID] as Int }

    fun getHryvniaSign(): Flow<Boolean> =
        context.settingStore.data.map { it[DisplayS.HRYVNIA_SIGN_PREFERENCE_KEY] ?: DisplayS.default[DisplayS.HRYVNIA_SIGN_ID] as Boolean}

}


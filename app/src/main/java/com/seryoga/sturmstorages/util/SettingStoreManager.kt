import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.seryoga.sturmstorages.model.DataS
import com.seryoga.sturmstorages.model.DesignS
//import com.seryoga.sturmstorages.model.DesignS.TEXT_STYLE_OF_HIGHLIGHT_ID
import com.seryoga.sturmstorages.model.DisplayS
import com.seryoga.sturmstorages.model.SettingData
import com.seryoga.sturmstorages.util.Const
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


//val Context.settingStore: DataStore<Preferences> by preferencesDataStore(Const.SETTING_DATA_STORE)
val Context.settingStore: DataStore<Preferences> by preferencesDataStore(Const.SETTING_DATA_STORE)

class SettingStoreManager(val context: Context) {


    suspend fun saveURL(url: String) {
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


//    suspend fun saveBackgroundColor(element: String, colorInt: Int) {
//        context.settingStore.edit { pref ->
////            pref[intPreferencesKey(DesignS.map[element]?.get(3).toString())] = colorInt
//        }
//    }

//    suspend fun saveDifferentProviderColor(colorInt: Int) {
//        context.settingStore.edit { pref ->
////            pref[intPreferencesKey(DesignS.map[DesignS.PROVIDER_DESIGN]?.get(4).toString())] =
//                colorInt
//        }
//    }

//    suspend fun saveBackgroundActiveColor(colorInt: Int) {
//        context.settingStore.edit { pref ->
////            pref[intPreferencesKey(DesignS.map[DesignS.BACKGROUND_DESIGN]?.get(5).toString())] =
//                colorInt
//        }
//    }

// ___ Multi save ___
    suspend fun saveColor(element: String, colorInt: Int) {
        context.settingStore.edit { pref ->
            pref[intPreferencesKey(DesignS.map.getValue(element).color)] = colorInt
        }
    }

    suspend fun saveFontSize(element: String, fontSize: Int) {
        context.settingStore.edit { pref ->
            pref[intPreferencesKey(DesignS.map.getValue(element).fontSize)] = fontSize
        }
    }

    suspend fun saveFontFamily(element: String, fontFamily: String) {
        context.settingStore.edit { pref ->
            pref[stringPreferencesKey(DesignS.map.getValue(element).fontFamily)] = fontFamily
        }
    }

    suspend fun saveDecoration(element: String, decoration: String) {
        context.settingStore.edit { pref ->
            pref[stringPreferencesKey(DesignS.map.getValue(element).decoration)] = decoration
        }
    }


//  ___ Single save ___
    suspend fun saveColorOfProviderSecond(colorInt: Int) {
        context.settingStore.edit { pref ->
            pref[intPreferencesKey(DesignS.COLOR_OF_PROVIDER_SECOND_ID)] = colorInt
        }
    }
    suspend fun saveColorOfRowBackground(colorInt: Int) {
        context.settingStore.edit { pref ->
            pref[intPreferencesKey(DesignS.COLOR_OF_ROW_BACKGROUND_ID)] = colorInt
        }
    }
    suspend fun saveColorOfRowBackgroundActive(colorInt: Int) {
        context.settingStore.edit { pref ->
            pref[intPreferencesKey(DesignS.COLOR_OF_ROW_BACKGROUND_ACTIVE_ID)] = colorInt
        }
    }
    suspend fun saveColorOfProviderBackground(colorInt: Int) {
        context.settingStore.edit { pref ->
            pref[intPreferencesKey(DesignS.COLOR_OF_PROVIDER_BACKGROUND_ID)] = colorInt
        }
    }
    suspend fun saveColorOfHighlightBackground(colorInt: Int) {
        context.settingStore.edit { pref ->
            pref[intPreferencesKey(DesignS.COLOR_OF_HIGHLIGHT_BACKGROUND_ID)] = colorInt
        }
    }

    val settingsFlow: Flow<SettingData> = context.settingStore.data.map { pref ->
        SettingData(
            url = pref[DataS.URL_PREFERENCE_KEY] ?: DataS.default[DataS.URL_ID] as String,
            isAutoupdate = pref[DataS.AUTOUPDATE_PREFERENCE_KEY]
                ?: DataS.default[DataS.AUTOUPDATE_ID] as Boolean,
            themeType = pref[DisplayS.THEME_PREFERENCE_KEY]
                ?: DisplayS.default[DisplayS.THEME_ID] as Boolean,
            displayType = pref[DisplayS.DISPLAY_PREFERENCE_KEY]
                ?: DisplayS.default[DisplayS.DISPLAY_ID] as Int,
            hryvniaSign = pref[DisplayS.HRYVNIA_SIGN_PREFERENCE_KEY]
                ?: DisplayS.default[DisplayS.HRYVNIA_SIGN_ID] as Boolean,

            colorOfProduct = pref[DesignS.COLOR_OF_PRODUCT_PREFERENCES_KEY]
                ?: DesignS.default[DesignS.COLOR_OF_PRODUCT_ID] as Int,
            fontSizeOfProduct = pref[DesignS.FONT_SIZE_OF_PRODUCT_PREFERENCES_KEY]
                ?: DesignS.default[DesignS.FONT_SIZE_OF_PRODUCT_ID] as Int,
            fontFamilyOfProduct = pref[DesignS.FONT_FAMILY_OF_PRODUCT_PREFERENCES_KEY]
                ?: DesignS.default[DesignS.FONT_FAMILY_OF_PRODUCT_ID] as String,
            decorationOfProduct = pref[DesignS.DECORATION_OF_PRODUCT_PREFERENCE_KEY]
                ?: DesignS.default[DesignS.DECORATION_OF_PRODUCT_ID] as String,
//            fontStyleOfProduct = pref[DesignS.FONT_STYLE_OF_PRODUCT_PREFERENCES_KEY] ?: FontStyle.THIN,

            colorOfPrice = pref[DesignS.COLOR_OF_PRICE_PREFERENCES_KEY]
                ?: DesignS.default[DesignS.COLOR_OF_PRICE_ID] as Int,
            fontSizeOfPrice = pref[DesignS.FONT_SIZE_OF_PRICE_PREFERENCES_KEY]
                ?: DesignS.default[DesignS.FONT_SIZE_OF_PRICE_ID] as Int,
            fontFamilyOfPrice = pref[DesignS.FONT_FAMILY_OF_PRICE_PREFERENCES_KEY]
                ?: DesignS.default[DesignS.FONT_FAMILY_OF_PRICE_ID] as String,
            decorationOfPrice = pref[DesignS.DECORATION_OF_PRICE_PREFERENCE_KEY]
                ?: DesignS.default[DesignS.DECORATION_OF_PRICE_ID] as String,
//            fontStyleOfPrice = pref[DesignS.FONT_STYLE_OF_PRICE_PREFERENCES_KEY] ?: FontStyle.THIN,

            colorOfQuantity = pref[DesignS.COLOR_OF_QUANTITY_PREFERENCES_KEY]
                ?: DesignS.default[DesignS.COLOR_OF_QUANTITY_ID] as Int,
            fontSizeOfQuantity = pref[DesignS.FONT_SIZE_OF_QUANTITY_PREFERENCES_KEY]
                ?: DesignS.default[DesignS.FONT_SIZE_OF_QUANTITY_ID] as Int,
            fontFamilyOfQuantity = pref[DesignS.FONT_FAMILY_OF_QUANTITY_PREFERENCES_KEY]
                ?: DesignS.default[DesignS.FONT_FAMILY_OF_QUANTITY_ID] as String,
            decorationOfQuantity = pref[DesignS.DECORATION_OF_QUANTITY_PREFERENCE_KEY]
                ?: DesignS.default[DesignS.DECORATION_OF_QUANTITY_ID] as String,
//            fontStyleOfQuantity = pref[DesignS.FONT_STYLE_OF_QUANTITY_PREFERENCES_KEY] ?: FontStyle.THIN,

            colorOfProvider = pref[DesignS.COLOR_OF_PROVIDER_PREFERENCES_KEY]
                ?: DesignS.default[DesignS.COLOR_OF_PROVIDER_ID] as Int,
            fontSizeOfProvider = pref[DesignS.FONT_SIZE_OF_PROVIDER_PREFERENCES_KEY]
                ?: DesignS.default[DesignS.FONT_SIZE_OF_PROVIDER_ID] as Int,
            fontFamilyOfProvider = pref[DesignS.FONT_FAMILY_OF_PROVIDER_PREFERENCES_KEY]
                ?: DesignS.default[DesignS.FONT_FAMILY_OF_PROVIDER_ID] as String,
            decorationOfProvider = pref[DesignS.DECORATION_OF_PROVIDER_PREFERENCE_KEY]
                ?: DesignS.default[DesignS.DECORATION_OF_PROVIDER_ID] as String,
//            fontStyleOfProvider = pref[DesignS.FONT_STYLE_OF_PROVIDER_PREFERENCES_KEY] ?: FontStyle.THIN,

            colorOfProviderSecond = pref[DesignS.COLOR_OF_PROVIDER_SECOND_PREFERENCES_KEY]
                ?: DesignS.default[DesignS.COLOR_OF_PROVIDER_SECOND_ID] as Int,
            colorOfProviderBackground = pref[DesignS.COLOR_OF_PROVIDER_BACKGROUND_PREFERENCES_KEY]
                ?: DesignS.default[DesignS.COLOR_OF_PROVIDER_BACKGROUND_ID] as Int,
            colorOfRowBackground = pref[DesignS.COLOR_OF_ROW_BACKGROUND_PREFERENCES_KEY]
                ?: DesignS.default[DesignS.COLOR_OF_ROW_BACKGROUND_ID] as Int,
            colorOfRowBackgroundActive = pref[DesignS.COLOR_OF_ROW_BACKGROUND_ACTIVE_PREFERENCES_KEY]
                ?: DesignS.default[DesignS.COLOR_OF_ROW_BACKGROUND_ACTIVE_ID] as Int,

            colorOfHighlight = pref[DesignS.COLOR_OF_HIGHLIGHT_PREFERENCES_KEY]
                ?: DesignS.default[DesignS.COLOR_OF_HIGHLIGHT_ID] as Int,
            fontSizeOfHighlight = pref[DesignS.FONT_SIZE_OF_HIGHLIGHT_PREFERENCE_KEY]
                ?: DesignS.default[DesignS.FONT_SIZE_OF_HIGHLIGHT_ID] as Int,
            fontFamilyOfHighlight = pref[DesignS.FONT_FAMILY_OF_HIGHLIGHT_PREFERENCE_KEY]
                ?: DesignS.default[DesignS.FONT_FAMILY_OF_HIGHLIGHT_ID] as String,
            colorOfHighlightBackground = pref[DesignS.COLOR_OF_HIGHLIGHT_BACKGROUND_PREFERENCE_KEY]
                ?: DesignS.default[DesignS.COLOR_OF_HIGHLIGHT_BACKGROUND_ID] as Int,

            decorationOfHighlight = pref[DesignS.DECORATION_OF_HIGHLIGHT_PREFERENCE_KEY]
                ?: DesignS.default[DesignS.DECORATION_OF_HIGHLIGHT_ID] as String,
//            textStyleOfHighlight = pref[DesignS.TEXT_STYLE_OF_HIGHLIGHT_PREFERENCE_KEY]
//                ?: DesignS.default[DesignS.TEXT_STYLE_OF_HIGHLIGHT_ID] as String,
        )
    }
    //  ___ Main ___
    fun getURL(): Flow<String> =
        context.settingStore.data.map {
            it[DataS.URL_PREFERENCE_KEY] ?: DataS.default[DataS.URL_ID] as String
        }

    fun getAutoUpdateType(): Flow<Boolean> =
        context.settingStore.data.map {
            it[DataS.AUTOUPDATE_PREFERENCE_KEY] ?: DataS.default[DataS.AUTOUPDATE_ID] as Boolean
        }

    fun getThemeType(): Flow<Boolean> =
        context.settingStore.data.map {
            it[DisplayS.THEME_PREFERENCE_KEY] ?: DisplayS.default[DisplayS.THEME_ID] as Boolean
        }

    fun getDisplayType(): Flow<Int> =
        context.settingStore.data.map {
            it[DisplayS.DISPLAY_PREFERENCE_KEY] ?: DisplayS.default[DisplayS.DISPLAY_ID] as Int
        }

    fun getHryvniaSign(): Flow<Boolean> =
        context.settingStore.data.map {
            it[DisplayS.HRYVNIA_SIGN_PREFERENCE_KEY]
                ?: DisplayS.default[DisplayS.HRYVNIA_SIGN_ID] as Boolean
        }


    //  ___ Product ___
    fun getColorOfProduct(): Flow<Int> =
        context.settingStore.data.map {
            it[DesignS.COLOR_OF_PRODUCT_PREFERENCES_KEY]
                ?: DesignS.default.getValue(DesignS.COLOR_OF_PRODUCT_ID) as Int
        }

    fun getFontSizeOfProduct(): Flow<Int> =
        context.settingStore.data.map {
            it[DesignS.FONT_SIZE_OF_PRODUCT_PREFERENCES_KEY]
                ?: DesignS.default.getValue(DesignS.FONT_SIZE_OF_PRODUCT_ID) as Int
        }

    fun getFontFamilyOfProduct(): Flow<String> =
        context.settingStore.data.map {
            it[DesignS.FONT_FAMILY_OF_PRODUCT_PREFERENCES_KEY]
                ?: DesignS.default.getValue(DesignS.FONT_FAMILY_OF_PRODUCT_ID) as String
        }

    fun getDecorationsOfProduct(): Flow<String> =
        context.settingStore.data.map {
            it[DesignS.DECORATION_OF_PRODUCT_PREFERENCE_KEY]
                ?: DesignS.default.getValue(DesignS.DECORATION_OF_PRODUCT_ID) as String
        }

    //  ___ Price ___
    fun getColorOfPrice(): Flow<Int> =
        context.settingStore.data.map {
            it[DesignS.COLOR_OF_PRICE_PREFERENCES_KEY]
                ?: DesignS.default.getValue(DesignS.COLOR_OF_PRICE_ID) as Int
        }

    fun getFontSizeOfPrice(): Flow<Int> =
        context.settingStore.data.map {
            it[DesignS.FONT_SIZE_OF_PRICE_PREFERENCES_KEY]
                ?: DesignS.default.getValue(DesignS.FONT_SIZE_OF_PRICE_ID) as Int
        }

    fun getFontFamilyOfPrice(): Flow<String> =
        context.settingStore.data.map {
            it[DesignS.FONT_FAMILY_OF_PRICE_PREFERENCES_KEY]
                ?: DesignS.default.getValue(DesignS.FONT_FAMILY_OF_PRICE_ID) as String
        }

    fun getDecorationsOfPrice(): Flow<String> =
        context.settingStore.data.map {
            it[DesignS.DECORATION_OF_PRICE_PREFERENCE_KEY]
                ?: DesignS.default.getValue(DesignS.DECORATION_OF_PRICE_ID) as String
        }


    //  ___ Quantity ___
    fun getColorOfQuantity(): Flow<Int> =
        context.settingStore.data.map {
            it[DesignS.COLOR_OF_QUANTITY_PREFERENCES_KEY]
                ?: DesignS.default.getValue(DesignS.COLOR_OF_QUANTITY_ID) as Int
        }

    fun getFontSizeOfQuantity(): Flow<Int> =
        context.settingStore.data.map {
            it[DesignS.FONT_SIZE_OF_QUANTITY_PREFERENCES_KEY]
                ?: DesignS.default.getValue(DesignS.FONT_SIZE_OF_QUANTITY_ID) as Int
        }

    fun getFontFamilyOfQuantity(): Flow<String> =
        context.settingStore.data.map {
            it[DesignS.FONT_FAMILY_OF_QUANTITY_PREFERENCES_KEY]
                ?: DesignS.default.getValue(DesignS.FONT_FAMILY_OF_QUANTITY_ID) as String
        }

    fun getDecorationsOfQuantity(): Flow<String> =
        context.settingStore.data.map {
            it[DesignS.DECORATION_OF_QUANTITY_PREFERENCE_KEY]
                ?: DesignS.default.getValue(DesignS.DECORATION_OF_QUANTITY_ID) as String
        }


    //  ___ Provider ___
    fun getColorOfProvider(): Flow<Int> =
        context.settingStore.data.map {
            it[DesignS.COLOR_OF_PROVIDER_PREFERENCES_KEY]
                ?: DesignS.default.getValue(DesignS.COLOR_OF_PROVIDER_ID) as Int
        }

    fun getFontSizeOfProvider(): Flow<Int> =
        context.settingStore.data.map {
            it[DesignS.FONT_SIZE_OF_PROVIDER_PREFERENCES_KEY]
                ?: DesignS.default.getValue(DesignS.FONT_SIZE_OF_PROVIDER_ID) as Int
        }

    fun getFontFamilyOfProvider(): Flow<String> =
        context.settingStore.data.map {
            it[DesignS.FONT_FAMILY_OF_PROVIDER_PREFERENCES_KEY]
                ?: DesignS.default.getValue(DesignS.FONT_FAMILY_OF_PROVIDER_ID) as String
        }

    fun getDecorationsOfProvider(): Flow<String> =
        context.settingStore.data.map {
            it[DesignS.DECORATION_OF_PROVIDER_PREFERENCE_KEY]
                ?: DesignS.default.getValue(DesignS.DECORATION_OF_PROVIDER_ID) as String
        }


    //  ___ Second Provider ___
    fun getColorOfProviderSecond(): Flow<Int> =
        context.settingStore.data.map {
            it[DesignS.COLOR_OF_PROVIDER_SECOND_PREFERENCES_KEY]
                ?: DesignS.default.getValue(DesignS.COLOR_OF_PROVIDER_SECOND_ID) as Int
        }

    fun getColorOfProviderBackground(): Flow<Int> =
        context.settingStore.data.map {
            it[DesignS.COLOR_OF_PROVIDER_BACKGROUND_PREFERENCES_KEY]
                ?: DesignS.default.getValue(DesignS.COLOR_OF_PROVIDER_BACKGROUND_ID) as Int
        }
    fun getColorOfRowBackground(): Flow<Int> =
        context.settingStore.data.map {
            it[DesignS.COLOR_OF_ROW_BACKGROUND_PREFERENCES_KEY]
                ?: DesignS.default.getValue(DesignS.COLOR_OF_ROW_BACKGROUND_ID) as Int
        }
    fun getColorOfRowBackgroundActive(): Flow<Int> =
        context.settingStore.data.map {
            it[DesignS.COLOR_OF_ROW_BACKGROUND_ACTIVE_PREFERENCES_KEY]
                ?: DesignS.default.getValue(DesignS.COLOR_OF_ROW_BACKGROUND_ACTIVE_ID) as Int
        }


    //  ___ Highlight ___
    fun getColorOfHighlight(): Flow<Int> =
        context.settingStore.data.map {
            it[DesignS.COLOR_OF_HIGHLIGHT_PREFERENCES_KEY]
                ?: DesignS.default.getValue(DesignS.COLOR_OF_HIGHLIGHT_ID) as Int
        }

    fun getFontSizeOfHighlight(): Flow<Int> =
        context.settingStore.data.map {
            it[DesignS.FONT_SIZE_OF_HIGHLIGHT_PREFERENCE_KEY]
                ?: DesignS.default.getValue(DesignS.FONT_SIZE_OF_HIGHLIGHT_ID) as Int
        }

    fun getFontFamilyOfHighlight(): Flow<String> =
        context.settingStore.data.map {
            it[DesignS.FONT_FAMILY_OF_HIGHLIGHT_PREFERENCE_KEY]
                ?: DesignS.default.getValue(DesignS.FONT_FAMILY_OF_HIGHLIGHT_ID) as String
        }

    fun getDecorationsOfHighlight(): Flow<String> =
        context.settingStore.data.map {
            it[DesignS.DECORATION_OF_HIGHLIGHT_PREFERENCE_KEY]
                ?: DesignS.default.getValue(DesignS.DECORATION_OF_HIGHLIGHT_ID) as String
        }

//    fun observeAllPreferenceChanges(context: Context) {
//        CoroutineScope(Dispatchers.IO).launch {
//            context.settingStore.data
//                .distinctUntilChanged() // запобігає дублюючим викликам
//                .collect { preferences ->
//                    Log.d("DataStore", "🔄 Preferences changed")
//
//                    preferences.asMap().forEach { (prefKey, value) ->
//                        Log.d("DataStore", "📌 ${prefKey.name} = $value")
//                    }
//                }
//        }
//    }
}


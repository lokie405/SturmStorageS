import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.seryoga.sturmstorages.model.SettingData
import com.seryoga.sturmstorages.util.Const
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


//val Context.settingStore: DataStore<Preferences> by preferencesDataStore(Const.SETTING_DATA_STORE)
    private val Context.settingStore: DataStore<Preferences> by preferencesDataStore(Const.SETTING_DATA_STORE)

object SettingStoreManager {

    private val DISPLAY_TYPE = intPreferencesKey(Const.DISPLAY_TYPE)


    suspend fun saveAllSetting(context: Context, settingData: SettingData) {
        context.settingStore.edit { pref ->
            pref[intPreferencesKey(Const.DISPLAY_TYPE)]
        }
    }

    suspend fun saveDisplayType(context: Context, displayType: Int) {
        context.settingStore.edit { pref ->
            pref[DISPLAY_TYPE] = displayType
        }
    }

    fun getSetting(context: Context) = context.settingStore.data.map { pref ->
        return@map SettingData(
            pref[DISPLAY_TYPE] ?: Const.DISPLAY_TYPE_ALL_IN_ROW
        )
    }

    fun getDisplayType(context: Context): Flow<Int> =
        context.settingStore.data.map { it[DISPLAY_TYPE] ?: Const.DISPLAY_TYPE_ALL_IN_ROW }
}
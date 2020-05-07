package apps.mithari.mvvmsampleapplication.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

private const val KEY_SAVED_AT = "key_saved_at"

class PreferenceProvider(context: Context) {
    private val appContext = context.applicationContext
//    we do not store context instead we get application context from it and use it

    //    we need to implement dependency for androidx preferenceManager
    private val preference: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun saveLastSavedAt(savedAt: String) {
        preference.edit().putString(KEY_SAVED_AT, savedAt).apply()
    }

    fun getLastSaveAt(): String? {
        return preference.getString(KEY_SAVED_AT, null)
    }
}
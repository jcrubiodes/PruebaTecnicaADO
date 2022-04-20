package mx.com.multiplica.ado

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {

    val PREFS_FILENAME = "sharePreferencesMonitoreo"

    var MINOMBRE = "MiNombre"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0);

    var miNombre: String?
        get() = prefs.getString(MINOMBRE, "")
        set(value) = prefs.edit().putString(MINOMBRE, value).apply()
}
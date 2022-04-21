package mx.com.multiplica.ado

import android.app.Application
import mx.com.multiplica.ado.utils.Prefs

class App : Application() {
    companion object {
        var prefs: Prefs? = null
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        prefs = Prefs(applicationContext)
    }
}
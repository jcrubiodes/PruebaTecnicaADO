package mx.com.multiplica.ado.youtube

import mx.com.multiplica.ado.App
import mx.com.multiplica.ado.R

class DeveloperKey {

    companion object {
        val DEVELOPER_KEY: String = App.instance.getString(R.string.key_youtube)
    }
}
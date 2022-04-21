package mx.com.multiplica.ado.webservice

import android.content.Context
import android.net.ConnectivityManager
import mx.com.multiplica.ado.App

class Network {
    companion object {
        fun hayRed(): Boolean {
            val conectivity =
                App.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = conectivity.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    }
}
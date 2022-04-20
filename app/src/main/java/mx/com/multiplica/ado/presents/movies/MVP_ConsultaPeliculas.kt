package mx.com.multiplica.ado.presents.movies

import android.net.Uri
import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mx.com.multiplica.ado.beans.DetailsMovie
import mx.com.multiplica.ado.beans.ResultsMovie
import mx.com.multiplica.ado.utils.Constantes
import mx.com.multiplica.ado.webservice.WebServicesNew

class MVP_ConsultaPeliculas(var contrato: ContratoPeliculas.Vista) : ContratoPeliculas.Presentador {

    override fun consultingListMovies(pagination: Int) {
        contrato.actualizaProgress(0)
        try {
            CoroutineScope(Dispatchers.IO).launch {
                // https://api.themoviedb.org/3/movie/top_rated?api_key=f7cc403f95df11731e14800b17bf0c43&language=es-MX&page=1
                val urlURI = Uri.parse(Constantes.getURL(0, 0)).buildUpon()
                    .appendQueryParameter(Constantes.STR_APIKEY, Constantes.STR_APIKEY_VALUE)
                    .appendQueryParameter(Constantes.STR_PAGE, pagination.toString())
                    .appendQueryParameter(
                        Constantes.STR_LENGUAGE,
                        Constantes.STR_LENGUAGE_VALUE
                    )
                    .build()
                val respuesta: String =
                    WebServicesNew.request(urlURI)
                contrato.actualizaProgress(10)
                withContext(Dispatchers.Main) {
                    Log.e(Constantes.getTagConsole(), "$respuesta")
                    val gson = Gson()
                    contrato.actualizaProgress(30)
                    val res = gson.fromJson(
                        respuesta,
                        ResultsMovie::class.java
                    )
                    contrato.actualizaProgress(40)
                    contrato.actualizaVista(res)
                    contrato.actualizaProgress(100)
                    Log.e(Constantes.getTagConsole(), "res->$res")
                }
            }
        } catch (ex: Exception) {
            Log.d(Constantes.getTagConsole(), "error->$ex")
        }
    }
}
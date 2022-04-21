package mx.com.multiplica.ado.presents.movies

import android.net.Uri
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mx.com.multiplica.ado.utils.Constantes
import mx.com.multiplica.ado.webservice.WebServicesNew

class MVP_ConsultaPeliculas(var contrato: ContratoPeliculas.Vista) : ContratoPeliculas.Presentador {

    override fun consultingListMovies(pagination: Int, method: Int, subMethod: Int) {
        contrato.actualizaProgress(0)
        try {
            CoroutineScope(Dispatchers.IO).launch {
                // https://api.themoviedb.org/3/movie/top_rated?api_key=f7cc403f95df11731e14800b17bf0c43&language=es-MX&page=1
                val urlURI = Uri.parse(Constantes.getURL(method, subMethod)).buildUpon()
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
                    contrato.actualizaProgress(30)
                    contrato.respuestaWS(respuesta)
                    contrato.actualizaProgress(40)
                    contrato.actualizaProgress(100)
                }
            }
        } catch (ex: Exception) {
            Log.d(Constantes.getTagConsole(), "error->$ex")
        }
    }

    override fun consultingListMoviesSearchs(
        pagination: Int,
        method: Int,
        subMethod: Int,
        wordSearc: String
    ) {
        contrato.actualizaProgress(0)
        try {
            CoroutineScope(Dispatchers.IO).launch {
                // https://api.themoviedb.org/3/movie/top_rated?api_key=f7cc403f95df11731e14800b17bf0c43&language=es-MX&page=1
                val urlURI = Uri.parse(Constantes.getURL(method, subMethod)).buildUpon()
                    .appendQueryParameter(Constantes.STR_APIKEY, Constantes.STR_APIKEY_VALUE)
                    .appendQueryParameter(Constantes.STR_PAGE, pagination.toString())
                    .appendQueryParameter(Constantes.STR_WITHWORK, wordSearc)
                    .appendQueryParameter(
                        Constantes.STR_LENGUAGE,
                        Constantes.STR_LENGUAGE_VALUE
                    )
                    .build()
                val respuesta: String =
                    WebServicesNew.request(urlURI)
                contrato.actualizaProgress(10)
                withContext(Dispatchers.Main) {
                    contrato.actualizaProgress(30)
                    contrato.respuestaWS(respuesta)
                    contrato.actualizaProgress(40)
                    contrato.actualizaProgress(100)
                }
            }
        } catch (ex: Exception) {
            Log.d(Constantes.getTagConsole(), "error->$ex")
        }
    }
}
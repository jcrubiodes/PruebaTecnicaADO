package mx.com.multiplica.ado.presents.detailsmovie

import android.net.Uri
import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mx.com.multiplica.ado.beans.DetailsMovie
import mx.com.multiplica.ado.beans.ResultsYouTubeVideo
import mx.com.multiplica.ado.utils.Constantes
import mx.com.multiplica.ado.webservice.WebServicesNew

class MVP_ConsultingMovieDetail(var contrato: ContratoDetail.Vista) : ContratoDetail.Presentador {

    override fun consultingDetailMovie(idMovie: Int) {
        contrato.actualizaProgress(0)
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val urlURI =
                    Uri.parse(Constantes.getURL(0, 1) + idMovie.toString()).buildUpon()
                        .appendQueryParameter(Constantes.STR_APIKEY, Constantes.STR_APIKEY_VALUE)
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
                        DetailsMovie::class.java
                    )
                    contrato.actualizaProgress(40)
                    contrato.actualizaVistaDetail(res)
                    contrato.actualizaProgress(100)
                    Log.e(Constantes.getTagConsole(), "res->$res")
                }
            }
        } catch (ex: Exception) {
            Log.d(Constantes.getTagConsole(), "error->$ex")
        }
    }

    override fun consultingYouTubeMovie(idMovie: Int) {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val urlURI =
                    Uri.parse(
                        Constantes.getURL(0, 1) + idMovie.toString()
                                + "/videos"
                    ).buildUpon()
                        .appendQueryParameter(Constantes.STR_APIKEY, Constantes.STR_APIKEY_VALUE)
                        .appendQueryParameter(
                            Constantes.STR_LENGUAGE,
                            Constantes.STR_LENGUAGE_VALUE
                        )
                        .build()
                val respuesta: String =
                    WebServicesNew.request(urlURI)
                withContext(Dispatchers.Main) {
                    Log.e(Constantes.getTagConsole(), "$respuesta")
                    val gson = Gson()
                    val res = gson.fromJson(
                        respuesta,
                        ResultsYouTubeVideo::class.java
                    )
                    contrato.mostrarVideoYouTube(res)
                    Log.e(Constantes.getTagConsole(), "res->$res")
                }
            }
        } catch (ex: Exception) {
            Log.d(Constantes.getTagConsole(), "error->$ex")
        }
    }
}
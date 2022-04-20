package mx.com.multiplica.ado.webservice

import android.net.Uri
import android.util.Log
import kotlinx.coroutines.suspendCancellableCoroutine
import mx.com.multiplica.ado.utils.Constantes
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStreamReader
import java.io.Reader
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import kotlin.coroutines.resume

class WebServicesNew() {

    companion object {

        val POST: String = "POST"
        var hayInternet: Boolean = false
        var conectoServidor: Boolean = false
        var mensajeError: String? = null
        var conexionCorrecta = false

        //        var json: String = "";
        var webservice: Int = 0
        var resCodigo = 0
        private var loguearse = false


        suspend fun request(urlURI: Uri): String {
            return suspendCancellableCoroutine { continuation ->
                try {
                    val reader: BufferedReader
                    val url = URL(urlURI.toString())
                    Log.e(Constantes.getTagConsole(), "url->" + url.toString())
                    with(url.openConnection() as HttpURLConnection) {
                        Log.e(Constantes.getTagConsole(), "responseCode->$responseCode")
                        if (responseCode == HttpsURLConnection.HTTP_OK) {
                            reader = BufferedReader(InputStreamReader(inputStream) as Reader?)
                            val response = StringBuffer()
                            var inputLine = reader.readLine()
                            while (inputLine != null) {
                                response.append(inputLine)
                                inputLine = reader.readLine()
                            }
                            reader.close()
                            if (continuation.isActive) {
                                continuation.resume(response.toString())
                            }
                        } else {
                            if (continuation.isActive) {
//                                var version:Int = Constantes.version
//                                var mensaje = App.instance.getString(R.string.mensaje_sinconexion)
//                                val respuessta =
//                                    "{\"object\" :{}, \"version\":$version ," +
//                                            " \"success\" :$responseCode, \"fechaConsulta\" : \"\" , " +
//                                            "\"message\":\"$mensaje\"}"
//                                continuation.resume(respuessta)
                            }
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    if (continuation.isActive) {
//                        continuation.resumeWithException(e)
                    }
                } catch (exc: FileNotFoundException) {
                    exc.printStackTrace()
                }
            }
        }
    }
}
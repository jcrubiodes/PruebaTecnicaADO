package mx.com.multiplica.ado.webservice

import org.json.JSONObject

interface CompletadoListener {

    fun peticionCorrecta(jsonObject: JSONObject, option: Int)
    fun peticionIncorrecta(mensaje: String, option: Int, resCodigo: Int, loguearse: Boolean)
    fun noHayInternet()
}
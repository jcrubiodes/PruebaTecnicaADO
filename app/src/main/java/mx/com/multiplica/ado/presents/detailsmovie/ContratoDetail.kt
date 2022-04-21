package mx.com.multiplica.ado.presents.detailsmovie

import mx.com.multiplica.ado.beans.Details
import mx.com.multiplica.ado.beans.DetailsTV
import mx.com.multiplica.ado.beans.ResultsYouTubeVideo

class ContratoDetail {

    interface Vista {
        fun actualizaVistaDetail(details: Details)
        fun mostrarVideoYouTube(urlYouTubeVideo: ResultsYouTubeVideo)
        fun actualizaVistaDetailTV(details: DetailsTV)
        fun actualizaProgress(progress: Int)
        fun respuestaWS(respuesta: String)
    }

    interface Presentador {
        fun consultingDetailMovie(idMovie: Int, method: Int, subMethod: Int)
        fun consultingYouTubeMovie(idMovie: Int, method: Int, subMethod: Int)

    }
}
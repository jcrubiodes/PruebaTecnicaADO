package mx.com.multiplica.ado.presents.detailsmovie

import mx.com.multiplica.ado.beans.DetailsMovie
import mx.com.multiplica.ado.beans.ResultsYouTubeVideo

class ContratoDetail {

    interface Vista {
        fun actualizaVistaDetail(detailsMovie: DetailsMovie)
        fun mostrarVideoYouTube(urlYouTubeVideo: ResultsYouTubeVideo)
        fun actualizaProgress(progress: Int)
    }

    interface Presentador {
        fun consultingDetailMovie(idMovie: Int)
        fun consultingYouTubeMovie(idMovie: Int)
    }
}
package mx.com.multiplica.ado.presents.movies

import mx.com.multiplica.ado.beans.DetailsMovie
import mx.com.multiplica.ado.beans.ResultsMovie

class ContratoPeliculas {

    interface Vista {
        fun muestraToast(mensaje: String, tiempo: Int)
        fun actualizaVista(results: ResultsMovie)
        fun actualizaProgress(progress: Int)
    }

    interface Presentador {
        fun consultingListMovies(
            pagination: Int
        )
    }
}
package mx.com.multiplica.ado.presents.movies

class ContratoPeliculas {

    interface Vista {
        fun muestraToast(mensaje: String, tiempo: Int)
        fun actualizaProgress(progress: Int)
        fun respuestaWS(res: String)
    }

    interface Presentador {
        fun consultingListMovies(
            pagination: Int, method: Int, subMethod: Int
        )

        fun consultingListMoviesSearchs(
            pagination: Int,
            method: Int,
            subMethod: Int,
            wordSearc: String
        )
    }
}
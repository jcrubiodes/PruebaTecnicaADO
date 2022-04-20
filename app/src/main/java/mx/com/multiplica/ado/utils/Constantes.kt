package mx.com.multiplica.ado.utils

class Constantes {

    companion object {
        val STR_JSON: String = "json"
        private val miTAG: String = "tag_desa"
        private val IP: String = "api.themoviedb.org"
        private val ENDPOINT: String = "3"

        val ACTIONS_METHOD = arrayOf(
            "/movie",
            "/"
        )

        val ACTIONS_SUBMETHOD = arrayOf(
            "/top_rated",
            "/"
        )

        fun getTagConsole(): String {
            return miTAG
        }

        var CONTENT_TYPE = "Content-Type"
        var APLICATION_JSON = "application/json"
        var ACCEPT = "Accept"
        var UTF = "UTF-8"
        var VERSIONWEB = "version"
        var SUCCESS = "success"
        var MESSAGE = "message"

        const val STR_APIKEY = "api_key"
        const val STR_APIKEY_VALUE = "f7cc403f95df11731e14800b17bf0c43"
        const val STR_IDMOVIE = "38055"
        const val STR_PAGE = "page"
        const val STR_LENGUAGE = "language"
        const val STR_URLVIDEOYOUTUBE = "urlVideoYouTube"
//        const val STR_LENGUAGE_VALUE = "en-US"
        const val STR_LENGUAGE_VALUE = "es-MX"
        const val ID_MOVIE = "idMovie"

        //        https://api.themoviedb.org/3/movie/667257?api_key=f7cc403f95df11731e14800b17bf0c43&language=en-US
        fun getURL(webservices: Int, submethod: Int): String {
            var url: String =
                "https://" + IP + "/" + ENDPOINT + ACTIONS_METHOD[webservices] +
                        ACTIONS_SUBMETHOD[submethod]
            return url
        }

        fun getImageURL(size: String, img: String): String {
            val urlImg = "https://image.tmdb.org/t/p/$size$img"
            return urlImg
        }
    }
}
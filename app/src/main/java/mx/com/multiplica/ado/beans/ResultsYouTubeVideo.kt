package mx.com.multiplica.ado.beans

class ResultsYouTubeVideo(
    id: Int,
    results: ArrayList<VideoYouTube>
) {
    var id: Int = 0
    var results: ArrayList<VideoYouTube>

    init {
        this.id = id
        this.results = results
    }
}
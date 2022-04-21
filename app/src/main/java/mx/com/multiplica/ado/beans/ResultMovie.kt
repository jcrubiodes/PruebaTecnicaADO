package mx.com.multiplica.ado.beans

class ResultMovie(
    adult: Boolean,
    backdrop_path: String,
    genre_ids: ArrayList<Int>,
    id: Int,
    original_language: String,
    original_title: String,
    overview: String,
    popularity: Float,
    poster_path: String,
    release_date: String,
    title: String,
    video: Boolean,
    vote_average: Float,
    vote_count: Int
) {
    var adult: Boolean
    var backdrop_path: String
    var genre_ids: ArrayList<Int>
    var id: Int
    var original_language: String
    var original_title: String
    var overview: String
    var popularity: Float
    var poster_path: String
    var release_date: String
    var title: String
    var video: Boolean
    var vote_average: Float
    var vote_count: Int

    init {
        this.adult = adult
        this.backdrop_path = backdrop_path
        this.genre_ids = genre_ids
        this.id = id
        this.original_language = original_language
        this.original_title = original_title
        this.overview = overview
        this.popularity = popularity
        this.poster_path = poster_path
        this.release_date = release_date
        this.title = title
        this.video = video
        this.vote_average = vote_average
        this.vote_count = vote_count
    }

    override fun toString(): String {
        return "ResultMovie(adult=$adult, backdrop_path='$backdrop_path', genre_ids=$genre_ids, id=$id, original_language='$original_language', original_title='$original_title', overview='$overview', popularity=$popularity, poster_path='$poster_path', release_date='$release_date', title='$title', video=$video, vote_average=$vote_average, vote_count=$vote_count)"
    }

}
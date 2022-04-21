package mx.com.multiplica.ado.beans

class ResultSerie(
    backdrop_path: String,
    first_air_date: String,
    genre_ids: ArrayList<Int>,
    id: Int,
    name: String,
    original_language: String,
    original_name: String,
    origin_country: ArrayList<String>,
    overview: String,
    popularity: Float,
    poster_path: String,
    vote_average: Float,
    vote_count: Int
) {
    var backdrop_path: String
    var first_air_date: String
    var genre_ids: ArrayList<Int>
    var id: Int
    var name: String
    var original_language: String
    var original_name: String
    var origin_country: ArrayList<String>
    var overview: String
    var popularity: Float
    var poster_path: String
    var vote_average: Float
    var vote_count: Int

    init {
        this.backdrop_path = backdrop_path
        this.first_air_date = first_air_date
        this.genre_ids = genre_ids
        this.id = id
        this.name = name
        this.original_language = original_language
        this.original_name = original_name
        this.origin_country = origin_country
        this.overview = overview
        this.popularity = popularity
        this.poster_path = poster_path
        this.vote_average = vote_average
        this.vote_count = vote_count
    }
}
package mx.com.multiplica.ado.beans

class Details(
    adult: Boolean,
    backdrop_path: String,
    belongs_to_collection: Any,
    budget: Int,
    genres: ArrayList<Genres>,
    homepage: String,
    id: Int,
    imdb_id: String,
    original_language: String,
    original_title: String,
    overview: String,
    popularity: Float,
    poster_path: String,
    release_date: String,
    revenue: Int,
    runtime: Int,
    status: String,
    tagline: String,
    title: String,
    video: Boolean,
    vote_average: Float,
    vote_count: Int
) {
    var adult: Boolean
    var backdrop_path: String
    var belongs_to_collection: Any
    var budget: Int
    var genres: ArrayList<Genres>
    var homepage: String
    var id: Int
    var imdb_id: String
    var original_language: String
    var original_title: String
    var overview: String
    var popularity: Float
    var poster_path: String
    var release_date: String
    var revenue: Int
    var runtime: Int
    var status: String
    var tagline: String
    var title: String
    var video: Boolean
    var vote_average: Float
    var vote_count: Int

    init {
        this.adult = adult
        this.backdrop_path = backdrop_path
        this.belongs_to_collection = belongs_to_collection
        this.budget = budget
        this.genres = genres
        this.homepage = homepage
        this.id = id
        this.imdb_id = imdb_id
        this.original_language = original_language
        this.original_title = original_title
        this.overview = overview
        this.popularity = popularity
        this.poster_path = poster_path
        this.release_date = release_date
        this.revenue = revenue
        this.runtime = runtime
        this.status = status
        this.tagline = tagline
        this.title = title
        this.video = video
        this.vote_average = vote_average
        this.vote_count = vote_count
    }

    override fun toString(): String {
        return "DetailsMovie(adult=$adult, backdrop_path='$backdrop_path', belongs_to_collection=$belongs_to_collection, budget=$budget, genres=$genres, homepage='$homepage', id=$id, imdb_id='$imdb_id', original_language='$original_language', original_title='$original_title', overview='$overview', popularity=$popularity, poster_path='$poster_path', release_date='$release_date', revenue=$revenue, runtime=$runtime, status='$status', tagline='$tagline', title='$title', video=$video, vote_average=$vote_average, vote_count=$vote_count)"
    }
}
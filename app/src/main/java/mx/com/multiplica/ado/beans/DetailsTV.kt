package mx.com.multiplica.ado.beans

class DetailsTV(
    id: Int,
    original_title: String,
    name: String,
    overview: String,
    poster_path: String
) {
    var id: Int
    var name: String
    var original_title: String
    var overview: String
    var poster_path: String

    init {
        this.id = id
        this.name = original_title
        this.original_title = original_title
        this.overview = overview
        this.poster_path = poster_path
    }
}
package mx.com.multiplica.ado.beans

class DetailsTV(
    id: Int,
    name: String,
    overview: String,
    poster_path: String
) {
    var id: Int
    var name: String
    var overview: String
    var poster_path: String

    init {
        this.id = id
        this.name = name
        this.overview = overview
        this.poster_path = poster_path
    }
}
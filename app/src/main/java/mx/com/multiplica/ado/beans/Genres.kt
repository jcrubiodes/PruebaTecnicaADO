package mx.com.multiplica.ado.beans

class Genres(id: Int, name: String) {
    var id: Int
    var name: String

    init {
        this.id = id
        this.name = name
    }

    override fun toString(): String {
        return "{\"id\":$id, \"name\":\"$name\"}"
    }
}
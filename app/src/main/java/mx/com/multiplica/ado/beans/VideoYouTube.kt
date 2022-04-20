package mx.com.multiplica.ado.beans

class VideoYouTube(
    iso_3166_1: String,
    iso_639_1: String,
    key: String,
    name: String,
    official: Boolean,
    published_at: String,
    site: String,
    size: Int,
    type: String = ""
) {
    var iso_3166_1: String = ""
    var iso_639_1: String = ""
    var key: String = ""
    var name: String = ""
    var official: Boolean = false
    var published_at: String = ""
    var site: String = ""
    var size: Int = 0
    var type: String = ""

    init {
        this.iso_3166_1 = iso_3166_1
        this.iso_639_1 = iso_639_1
        this.key = key
        this.name = name
        this.official = official
        this.published_at = published_at
        this.site = site
        this.size = size
        this.type = type
    }

    override fun toString(): String {
        return "VideoYouTube(iso_3166_1='$iso_3166_1', iso_639_1='$iso_639_1', key='$key', name='$name', official=$official, published_at='$published_at', site='$site', size=$size, type='$type')"
    }
}
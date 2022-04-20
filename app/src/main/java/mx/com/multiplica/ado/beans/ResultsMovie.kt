package mx.com.multiplica.ado.beans

class ResultsMovie(
    page: Int,
    total_pages: Int,
    total_results: Int,
    results: ArrayList<ResultMovie>
) {
    var page: Int
    var total_pages: Int
    var total_results: Int
    var results: ArrayList<ResultMovie>

    init {
        this.page = page
        this.total_pages = total_pages
        this.total_results = total_results
        this.results = results
    }

    override fun toString(): String {
        return "json:{\"page\":$page, \"total_pages\":$total_pages, \"total_results\"," +
                "\"results\":[$results]}"
    }
}
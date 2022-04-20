package mx.com.multiplica.ado.beans

class ResponseWS(message: String, success: Int) {
    var message: String
    var success: Int = 0

    init {
        this.message = message
        this.success = success
    }
}
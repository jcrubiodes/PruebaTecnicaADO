package mx.com.multiplica.ado.beans

class ItemMenu
    (
    opcionWS: Int,
    idItem: Int,
    idIc: Int,
    texto: String
) {
    var idItem: Int = 0
    var idIc: Int = 0
    var opcionWS: Int
    var texto: String = ""

    init {
        this.idItem = idItem
        this.idIc = idIc
        this.texto = texto
        this.opcionWS = opcionWS
    }
}
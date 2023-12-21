package test.co.kosong.model

data class User(
    val userName: String,
    val userAge: String,
    val userOccupation: String,
    val userPassword: String
) {
    constructor(): this("", "", "","")
}
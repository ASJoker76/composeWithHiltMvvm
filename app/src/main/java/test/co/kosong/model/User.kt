package test.co.kosong.model

data class User(
    val userName: String,
    val userAge: String,
    val userOccupation: String,
    val userPassword: String,
    val userRole: Int
) {
    constructor(): this("", "", "","",0)
}
package demo.dto

data class CreateUserRequest(
    val mail:String,
    val firstName:String,
    val middleName: String,
    val postCode: String,
    val isActive:Boolean
)


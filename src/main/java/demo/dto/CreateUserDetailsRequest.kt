package demo.dto

data class CreateUserDetailsRequest(
    val city: String,
    val address: String,
    val country: String,
    val postCode: String,
    val phoneNumber: String,
    val userId: Long
)

data class UpdateUserDetailsRequest(
    val city: String,
    val address: String,
    val country: String,
    val postCode: String,
    val phoneNumber: String
)
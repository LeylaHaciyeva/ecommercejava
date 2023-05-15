package demo.dto

data class UserDto (
    val mail:String,
    val firstName:String,
    val middleName: String,
    val postCode: String,
    val userDetails:List<UserDetailsDto>
    )
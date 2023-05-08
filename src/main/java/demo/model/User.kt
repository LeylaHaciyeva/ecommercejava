package demo.model

import jakarta.persistence.*

@Entity
 data class User (
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?=null,
    val mail:String,
    val firstName:String,
    val middleName: String,
    val postCode: String

) {
   constructor() : this(null,"","","",""){

   }
}


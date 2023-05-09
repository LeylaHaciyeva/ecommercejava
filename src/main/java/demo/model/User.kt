package demo.model

import jakarta.persistence.*

@Entity
@Table(name = "_user")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
//    @Column(unique = true)
    val mail: String,
    val firstName: String,
    val middleName: String,
    val postCode: String,
    val isActive : Boolean


) {
    companion object {
        val STUB = User();
    }

    constructor() : this(null, "", "", "", "",true)
}
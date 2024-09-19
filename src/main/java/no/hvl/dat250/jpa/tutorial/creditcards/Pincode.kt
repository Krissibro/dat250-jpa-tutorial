package no.hvl.dat250.jpa.tutorial.creditcards

import jakarta.persistence.*

@Entity
data class Pincode(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val code: String = "",

    val count: Int = 0
) {
    // No-arg constructor for JPA
    constructor() : this(code = "", count = 0)
}

package no.hvl.dat250.jpa.tutorial.creditcards

import jakarta.persistence.*

@Entity
data class Address(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val street: String = "",

    val number: Int = 0,

    @ManyToMany(mappedBy = "addresses")
    val owners: Collection<Customer> = emptyList()
) {
    // No-arg constructor for JPA
    constructor() : this(street = "", number = 0)
}

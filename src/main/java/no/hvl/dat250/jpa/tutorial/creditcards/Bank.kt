package no.hvl.dat250.jpa.tutorial.creditcards

import jakarta.persistence.*

@Entity
data class Bank(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val name: String = "",

    @OneToMany(mappedBy = "owningBank")
    val ownedCards: Collection<CreditCard> = emptyList()
) {
    // No-arg constructor for JPA
    constructor() : this(name = "")
}

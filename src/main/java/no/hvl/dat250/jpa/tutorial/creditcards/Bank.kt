package no.hvl.dat250.jpa.tutorial.creditcards

import jakarta.persistence.*

@Entity
data class Bank(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val name: String = "",

    @OneToMany(mappedBy = "owningBank")
    var ownedCards: Collection<CreditCard> = emptyList()
)

package no.hvl.dat250.jpa.tutorial.creditcards

import jakarta.persistence.*

@Entity
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val name: String = "",

    @ManyToMany
    @JoinTable(
        name = "customer_address",
        joinColumns = [JoinColumn(name = "customer_id")],
        inverseJoinColumns = [JoinColumn(name = "address_id")]
    )
    val addresses: Collection<Address> = emptyList(),

    @OneToMany(mappedBy = "customer")
    val creditCards: Collection<CreditCard> = emptyList()
) {
    // No-arg constructor for JPA
    constructor() : this(name = "")
}

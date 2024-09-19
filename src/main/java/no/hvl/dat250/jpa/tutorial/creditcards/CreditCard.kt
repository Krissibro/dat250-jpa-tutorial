package no.hvl.dat250.jpa.tutorial.creditcards

import jakarta.persistence.*

@Entity
data class CreditCard(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val number: Int = 0,

    val balance: Int = 0,

    val creditLimit: Int = 0,

    @OneToOne
    val pincode: Pincode = Pincode(),

    @ManyToOne
    val owningBank: Bank = Bank()
) {
    // No-arg constructor for JPA
    constructor() : this(number = 0, balance = 0, creditLimit = 0)
}

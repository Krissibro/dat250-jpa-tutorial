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
    @JoinColumn(name = "pincode_id")
    val pincode: Pincode = Pincode(),

    @ManyToOne
    @JoinColumn(name = "customer_id")  // This defines the foreign key in the CreditCard table
    var customer: Customer? = null,

    @ManyToOne
    val owningBank: Bank = Bank()
)

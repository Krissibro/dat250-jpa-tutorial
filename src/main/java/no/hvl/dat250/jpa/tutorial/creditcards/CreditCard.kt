package no.hvl.dat250.jpa.tutorial.creditcards

import jakarta.persistence.*
import no.hvl.dat250.jpa.tutorial.creditcards.driver.CreditCardsMain

@Entity
data class CreditCard(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val number: Int = 0,

    val balance: Int = 0,

    val creditLimit: Int = 0,

    @ManyToOne
    @JoinColumn(name = "pincode_id")
    val pincode: Pincode = Pincode(),

    @ManyToOne
    @JoinColumn(name = "customer_id")  // This defines the foreign key in the CreditCard table
    var customer: Customer? = null,

    @ManyToOne
    val owningBank: Bank = Bank()
) {
    override fun toString(): String {
        return "CreditCard(id=$id, number=$number, balance=$balance, creditLimit=$creditLimit)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other?.javaClass) return false

        other as CreditCard

        return id != null && id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}

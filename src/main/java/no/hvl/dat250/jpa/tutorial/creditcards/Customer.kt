package no.hvl.dat250.jpa.tutorial.creditcards

import jakarta.persistence.*

@Entity
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var name: String = "",

    @ManyToMany
    @JoinTable(
        name = "customer_address",
        joinColumns = [JoinColumn(name = "customer_id")],
        inverseJoinColumns = [JoinColumn(name = "address_id")]
    )
    var addresses: Collection<Address> = emptyList(),

    @OneToMany(mappedBy = "customer")  // This points to the customer field in CreditCard
    var creditCards: Collection<CreditCard> = emptyList()
) {
    override fun toString(): String {
        return "Customer(id=$id, name=$name)"
    }
}


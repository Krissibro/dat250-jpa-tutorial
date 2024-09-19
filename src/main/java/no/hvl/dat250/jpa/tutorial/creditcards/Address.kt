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
    var owners: Set<Customer> = setOf()
) {
    override fun toString(): String {
        return "Address(id=$id, street=$street, number=$number)"
    }
}

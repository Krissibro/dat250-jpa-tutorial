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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other?.javaClass) return false

        other as Address

        return id != null && id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}

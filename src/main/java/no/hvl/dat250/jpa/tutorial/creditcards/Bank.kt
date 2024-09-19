package no.hvl.dat250.jpa.tutorial.creditcards

import jakarta.persistence.*

@Entity
data class Bank(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val name: String = "",

    @OneToMany(mappedBy = "owningBank")
    var ownedCards: Set<CreditCard> = emptySet()
) {
    override fun toString(): String {
        return "Bank(id=$id, name=$name)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other?.javaClass) return false

        other as Bank

        return id != null && id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}

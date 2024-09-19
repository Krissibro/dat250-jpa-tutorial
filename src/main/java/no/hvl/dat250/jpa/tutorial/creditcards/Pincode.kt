package no.hvl.dat250.jpa.tutorial.creditcards

import jakarta.persistence.*

@Entity
data class Pincode(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val code: String = "",

    val count: Int = 0
) {
    override fun toString(): String {
        return "Pincode(id=$id, code='$code', count=$count)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other?.javaClass) return false

        other as Pincode

        return id != null && id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}

package no.hvl.dat250.jpa.tutorial.creditcards.driver

import jakarta.persistence.EntityManager
import jakarta.persistence.Persistence
import no.hvl.dat250.jpa.tutorial.creditcards.*

object CreditCardsMain {
    const val PERSISTENCE_UNIT_NAME: String = "jpa-tutorial"

    @JvmStatic
    fun main(args: Array<String>) {
        Persistence.createEntityManagerFactory(
            PERSISTENCE_UNIT_NAME
        ).use { factory ->
            factory.createEntityManager().use { em ->
                em.transaction.begin()
                createObjects(em)
                em.transaction.commit()
            }
        }
    }

    private fun createObjects(em: EntityManager) {
        // Create the Customer
        val customer = Customer(name = "Max Mustermann")

        // Create the Address
        val address = Address(street = "Inndalsveien", number = 28)
        customer.addresses = listOf(address)
        address.owners = listOf(customer)

        // Create the Bank
        val bank = Bank(name = "Pengebank")

        // Create the first Pincode
        val firstPincode = Pincode(code = "123", count = 1)

        // Create the second Pincode (if you want different pincodes for each card)
        val secondPincode = Pincode(code = "456", count = 1)

        // Create the first CreditCard
        val firstCard = CreditCard(number = 12345, balance = -5000, creditLimit = -10000, pincode = firstPincode, owningBank = bank)

        // Create the second CreditCard
        val secondCard = CreditCard(number = 123, balance = 1, creditLimit = 2000, pincode = secondPincode, owningBank = bank)

        // Link credit cards to the customer
        customer.creditCards = listOf(firstCard, secondCard)

        // Add the cards to the bank's ownedCards
        bank.ownedCards = listOf(firstCard, secondCard)

        // Persist everything
        em.persist(customer)
        em.persist(address)
        em.persist(bank)
        em.persist(firstCard)
        em.persist(secondCard)
        em.persist(firstPincode)
        em.persist(secondPincode)
    }

}

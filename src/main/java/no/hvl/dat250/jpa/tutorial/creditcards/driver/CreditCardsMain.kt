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
        address.owners = setOf(customer)

        // Create the Bank
        val bank = Bank(name = "Pengebank")

        // Create the Pincode for the Credit cards
        val pincode = Pincode(code = "123", count = 1)

        // Create the Credit cards
        val firstCard = CreditCard(
            number = 12345,
            balance = -5000,
            creditLimit = -10000,
            pincode = pincode,
            owningBank = bank,
            customer = customer
        )

        val secondCard = CreditCard(number = 123,
            balance = 1,
            creditLimit = 2000,
            pincode = pincode,
            owningBank = bank,
            customer = customer
        )

        // Link credit cards to the customer
        customer.creditCards = listOf(firstCard, secondCard)

        // Add the cards to the bank's ownedCards
        bank.ownedCards = setOf(firstCard, secondCard)

        // Persist everything
        em.persist(customer)
        em.persist(address)
        em.persist(bank)
        em.persist(firstCard)
        em.persist(secondCard)
        em.persist(pincode)
    }
}

package no.hvl.dat250.jpa.tutorial.creditcards.driver

import jakarta.persistence.EntityManager
import jakarta.persistence.Persistence

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
        // TODO: Create object world as shown in the README.md.
    }
}

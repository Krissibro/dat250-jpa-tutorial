package no.hvl.dat250.jpa.tutorial.basicexample

import jakarta.persistence.EntityManager
import jakarta.persistence.Persistence

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        Persistence.createEntityManagerFactory("jpa-tutorial").use { factory ->
            factory.createEntityManager().use { em ->
                readAndPrintTodos(em)
                // Insert new object
                em.transaction.begin()
                var todo = Todo()
                todo.summary = "test summary"
                todo.description = "test description"
                em.persist(todo)
                em.transaction.commit()
                readAndPrintTodos(em)
            }
        }
    }

    private fun readAndPrintTodos(em: EntityManager) {
        val q = em.createQuery("select t from Todo t", Todo::class.java)
        val todoList: List<Todo> = q.resultList

        for (todo in todoList) {
            println(todo)
        }
        println("Size: " + todoList.size)
    }
}

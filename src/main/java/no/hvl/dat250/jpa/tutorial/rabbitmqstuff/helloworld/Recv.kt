package no.hvl.dat250.jpa.tutorial.rabbitmqstuff.helloworld

import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DeliverCallback

fun main() {
    val queueName = "hello"

    // Set up connection factory and connection to RabbitMQ
    val factory = ConnectionFactory()
    factory.host = "localhost"
    factory.username = "guest"
    factory.password = "guest"

    // Establish a connection and channel
    factory.newConnection().use { connection ->
        connection.createChannel().use { channel ->
            // Declare the same queue as in Send.kt to make sure it exists
            channel.queueDeclare(queueName, false, false, false, null)
            println(" [*] Waiting for messages. To exit press CTRL+C")

            // Define what happens when a message is delivered
            val deliverCallback = DeliverCallback { _, delivery ->
                val message = String(delivery.body, Charsets.UTF_8)
                println(" [x] Received '$message'")
            }

            // Consume messages from the queue
            channel.basicConsume(queueName, true, deliverCallback) { _ -> }
        }
    }
}

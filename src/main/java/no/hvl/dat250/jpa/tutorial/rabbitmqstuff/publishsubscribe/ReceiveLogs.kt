package no.hvl.dat250.jpa.tutorial.rabbitmqstuff.publishsubscribe

import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DeliverCallback

fun main() {
    val exchangeName = "logs"

    val factory = ConnectionFactory()
    factory.host = "localhost"
    factory.username = "guest"
    factory.password = "guest"

    factory.newConnection().use { connection ->
        connection.createChannel().use { channel ->
            // Declare the exchange
            channel.exchangeDeclare(exchangeName, "fanout")

            // Declare a non-durable, exclusive, auto-delete queue
            val queueName = channel.queueDeclare().queue

            // Bind the queue to the exchange
            channel.queueBind(queueName, exchangeName, "")

            println(" [*] Waiting for messages. To exit press CTRL+C")

            // Define the callback to process received messages
            val deliverCallback = DeliverCallback { _, delivery ->
                val message = String(delivery.body, Charsets.UTF_8)
                println(" [x] Received '$message'")
            }

            // Start consuming messages
            channel.basicConsume(queueName, true, deliverCallback) { _ -> }

            Thread.currentThread().join() // Prevents the main thread from finishing
        }
    }
}

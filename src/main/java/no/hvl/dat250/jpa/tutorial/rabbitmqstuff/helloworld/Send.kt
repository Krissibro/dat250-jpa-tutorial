package no.hvl.dat250.jpa.tutorial.rabbitmqstuff.helloworld

import com.rabbitmq.client.ConnectionFactory

fun main() {
    val queueName = "hello"

    // Set up connection factory and connection to RabbitMQ
    val factory = ConnectionFactory()
    factory.host = "localhost"  // Make sure RabbitMQ is running locally
    factory.username = "guest"
    factory.password = "guest"

    // Establish a connection and channel
    factory.newConnection().use { connection ->
        connection.createChannel().use { channel ->
            // Declare a queue
            channel.queueDeclare(queueName, false, false, false, null)

            // The message to send
            val message = "Hello World!"

            // Send the message to the queue
            channel.basicPublish("", queueName, null, message.toByteArray())
            println(" [x] Sent '$message'")
        }
    }
}

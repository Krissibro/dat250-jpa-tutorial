package no.hvl.dat250.jpa.tutorial.rabbitmqstuff.publishsubscribe

import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.MessageProperties

fun main(args: Array<String>) {
    val exchangeName = "logs"

    // Set up connection factory and connect to RabbitMQ
    val factory = ConnectionFactory()
    factory.host = "localhost"
    factory.username = "guest"
    factory.password = "guest"

    factory.newConnection().use { connection ->
        connection.createChannel().use { channel ->
            // Declare a fanout exchange
            channel.exchangeDeclare(exchangeName, "fanout")

            // Create the message (log) to send
            val message = if (args.isNotEmpty()) args.joinToString(" ") else "info: Hello World!"

            // Send the message to the exchange
            channel.basicPublish(exchangeName, "", null, message.toByteArray())
            println(" [x] Sent '$message'")
        }
    }
}

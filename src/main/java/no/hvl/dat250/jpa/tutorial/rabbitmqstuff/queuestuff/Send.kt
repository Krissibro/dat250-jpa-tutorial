package no.hvl.dat250.jpa.tutorial.rabbitmqstuff.queuestuff

import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.MessageProperties

fun main(args: Array<String>) {
    val queueName = "task_queue"

    // Set up connection factory and connect to RabbitMQ
    val factory = ConnectionFactory()
    factory.host = "localhost"
    factory.username = "guest"
    factory.password = "guest"

    factory.newConnection().use { connection ->
        connection.createChannel().use { channel ->
            // Declare a durable queue (persistent)
            channel.queueDeclare(queueName, true, false, false, null)

            // Send each argument (task) as a separate message
            for (message in args) {
                channel.basicPublish(
                    "",
                    queueName,
                    MessageProperties.PERSISTENT_TEXT_PLAIN,
                    message.toByteArray()
                )
                println(" [x] Sent '$message'")
            }
        }
    }
}



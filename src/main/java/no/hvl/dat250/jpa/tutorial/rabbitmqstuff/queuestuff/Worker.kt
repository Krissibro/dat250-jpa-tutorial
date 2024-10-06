package no.hvl.dat250.jpa.tutorial.rabbitmqstuff.queuestuff

import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DeliverCallback

fun main() {
    val queueName = "task_queue"

    val factory = ConnectionFactory()
    factory.host = "localhost"
    factory.username = "guest"
    factory.password = "guest"

    factory.newConnection().use { connection ->
        connection.createChannel().use { channel ->
            // Declare the same durable queue
            channel.queueDeclare(queueName, true, false, false, null)
            println(" [*] Waiting for messages. To exit press CTRL+C")

            // Set QoS (quality of service) to process only one message at a time
            channel.basicQos(1)

            // Define the delivery callback
            val deliverCallback = DeliverCallback { _, delivery ->
                val message = String(delivery.body, Charsets.UTF_8)
                println(" [x] Received '$message'")

                // Simulate work by counting dots in the message
                doWork(message)
                println(" [x] Done")

                // Manually acknowledge the message when done processing
                channel.basicAck(delivery.envelope.deliveryTag, false)
            }

            // Start consuming messages, with manual acknowledgment enabled
            channel.basicConsume(queueName, false, deliverCallback) { _ -> }

            // Keep the thread alive to listen to more messages
            Thread.currentThread().join() // Prevents the main thread from finishing
        }
    }
}

// Simulate work by sleeping for each dot in the message
fun doWork(task: String) {
    task.forEach {
        if (it == '.') Thread.sleep(1000)  // Sleep 1 second per dot
    }
}


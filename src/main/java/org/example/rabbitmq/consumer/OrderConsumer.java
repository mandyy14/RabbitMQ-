package org.example.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import org.example.rabbitmq.config.RabbitConfig;
import org.example.rabbitmq.model.OrderCreatedMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class OrderConsumer {

    private static final Logger log = LoggerFactory.getLogger(OrderConsumer.class);

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME, containerFactory = "rabbitListenerContainerFactory")
    public void processOrderCreated(OrderCreatedMessage message,
                                    Channel channel,
                                    @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        try {
            // lógica de negócio aqui
            log.info("Processing message: " + message);
            // se tudo OK:
            channel.basicAck(tag, false);
        } catch (Exception ex) {
            log.error("Error processing message: {}", ex.getMessage());
            // decisão: requeue ou descartar
            boolean requeue = false; // ou true, conforme lógica
            try {
                channel.basicNack(tag, false, requeue);
            } catch (Exception e) {
                log.error("⚠Failed to nack message: {}", e.getMessage());
            }
        }
    }
}

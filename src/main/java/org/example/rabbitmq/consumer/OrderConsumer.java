package org.example.rabbitmq.consumer;


import org.example.rabbitmq.config.RabbitConfig;
import org.example.rabbitmq.model.OrderCreatedMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component
public class OrderConsumer {

    private static final Logger log = LoggerFactory.getLogger(OrderConsumer.class);

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void processOrderCreated(OrderCreatedMessage message) {
        log.info("Received OrderCreatedMessage: {}", message);

    }
}
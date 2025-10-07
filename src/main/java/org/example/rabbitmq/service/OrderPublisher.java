package org.example.rabbitmq.service;


import org.example.rabbitmq.config.RabbitConfig;
import org.example.rabbitmq.model.OrderCreatedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@Service
public class OrderPublisher {

    private final RabbitTemplate rabbitTemplate;

    public OrderPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishOrderCreated(OrderCreatedMessage message) {
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE_NAME,
                RabbitConfig.ROUTING_KEY,
                message
        );
        System.out.println("Published OrderCreatedMessage: " + message);
    }
}
package org.example.rabbitmq.controller;

import org.example.rabbitmq.model.OrderCreatedMessage;
import org.example.rabbitmq.service.OrderPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderPublisher orderPublisher;

    public OrderController(OrderPublisher orderPublisher) {
        this.orderPublisher = orderPublisher;
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderCreatedMessage message) {
        orderPublisher.publishOrderCreated(message);
        return ResponseEntity.ok("Order message published: " + message.orderId());
    }
}

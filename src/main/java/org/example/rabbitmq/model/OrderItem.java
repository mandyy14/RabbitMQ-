package org.example.rabbitmq.model;

public record OrderItem(
        String productId,
        int quantity
) {}

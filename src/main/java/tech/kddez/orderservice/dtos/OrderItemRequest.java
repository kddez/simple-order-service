package tech.kddez.orderservice.dtos;

import tech.kddez.orderservice.entities.OrderItem;

public record OrderItemRequest(String product, int quantity, double price) {



}




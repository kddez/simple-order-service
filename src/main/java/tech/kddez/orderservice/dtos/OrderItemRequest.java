package tech.kddez.orderservice.dtos;

public record OrderItemRequest(String product, int quantity, double price) {

}

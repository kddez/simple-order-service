package tech.kddez.orderservice.dtos;

import java.util.List;

public record OrderRequest(String clientName, List<OrderItemRequest> orderItemRequests) {
}

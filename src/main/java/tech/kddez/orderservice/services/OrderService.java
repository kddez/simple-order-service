package tech.kddez.orderservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.kddez.orderservice.dtos.OrderItemRequest;
import tech.kddez.orderservice.dtos.OrderRequest;
import tech.kddez.orderservice.entities.Order;
import tech.kddez.orderservice.entities.OrderItem;
import tech.kddez.orderservice.repositories.OrderItemRepository;
import tech.kddez.orderservice.repositories.OrderRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {


    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public Order createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setClientName(orderRequest.clientName());
        order.setOrderDate(LocalDate.now());

        List<OrderItem> orderItems = orderRequest.orderItemRequests()
                .stream()
                .map(this::toEntity)
                .peek(orderItem -> orderItem.setOrder(order))
                .collect(Collectors.toList());

        double totalPrice = orderItems
                .stream()
                .mapToDouble(items -> items.getQuantity() * items.getPrice())
                .sum();

        order.setTotalPrice(totalPrice);
        order.setOrderItems(orderItems);
        orderRepository.save(order);

        orderItems.forEach(orderItemRepository::save);

        return order;
    }

    public List<Order> orderList() {
        return orderRepository.findAll();
    }

    public void deleteOrder() {
        orderRepository.deleteAll();
    }

    public OrderItem toEntity(OrderItemRequest orderItemRequest) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(orderItemRequest.product());
        orderItem.setQuantity(orderItemRequest.quantity());
        orderItem.setPrice(orderItemRequest.price());
        return orderItem;
    }

}


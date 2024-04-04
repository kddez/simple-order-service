package tech.kddez.orderservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.kddez.orderservice.dtos.OrderItemRequest;
import tech.kddez.orderservice.dtos.OrderRequest;
import tech.kddez.orderservice.entities.Order;
import tech.kddez.orderservice.entities.OrderItem;
import tech.kddez.orderservice.repositories.OrderItemRepository;
import tech.kddez.orderservice.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {


    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public Order createOrder(OrderRequest orderRequest){

        Order order = new Order();
        order.setClientName(orderRequest.clientName());
        List<OrderItem> orderItems = new ArrayList<>();
        double totalPrice = 0.0;

        for(OrderItemRequest itemRequest: orderRequest.orderItemRequests()){

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(itemRequest.product());
            orderItem.setQuantity(itemRequest.quantity());
            orderItem.setPrice(itemRequest.price());

            totalPrice += itemRequest.price() * itemRequest.quantity(); // Calcula o preço total
            orderItem.setOrder(order);
            orderItems.add(orderItem);
        }

        order.setTotalPrice(totalPrice); // Define o preço total
        order.setOrderItems(orderItems);
        Order entity = orderRepository.save(order);

        for(OrderItem item : orderItems){
            orderItemRepository.save(item);
        }

        return entity;
    }

    public List<Order> orderList(){
        return orderRepository.findAll();
    }

    public void deleteOrder(){orderRepository.deleteAll();
    }

}


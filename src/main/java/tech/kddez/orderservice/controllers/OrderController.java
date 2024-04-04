package tech.kddez.orderservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.kddez.orderservice.dtos.OrderRequest;
import tech.kddez.orderservice.entities.Order;
import tech.kddez.orderservice.services.OrderService;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody OrderRequest orderRequest){

        Order order = orderService.createOrder(orderRequest);
        return ResponseEntity.ok(order);

    }

    @GetMapping
    public ResponseEntity<List<Order>> listAllOrders(){

        return ResponseEntity.ok(orderService.orderList());

    }

    @DeleteMapping
    public ResponseEntity deleteOrder(){
        orderService.deleteOrder();
        return ResponseEntity.noContent().build();
    }



}

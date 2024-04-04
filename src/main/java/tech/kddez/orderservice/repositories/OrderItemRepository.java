package tech.kddez.orderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.kddez.orderservice.entities.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, String> {
}

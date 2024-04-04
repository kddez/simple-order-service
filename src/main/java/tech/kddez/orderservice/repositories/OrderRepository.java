package tech.kddez.orderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.kddez.orderservice.entities.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
}

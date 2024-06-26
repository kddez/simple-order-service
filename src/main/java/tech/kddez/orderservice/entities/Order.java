package tech.kddez.orderservice.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String orderId;
    @CreatedDate
    private LocalDate orderDate;
    private String clientName;
    private Double totalPrice;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;

    @PrePersist
    public void prePersist(){
        this.orderDate = LocalDate.now();
    }

}

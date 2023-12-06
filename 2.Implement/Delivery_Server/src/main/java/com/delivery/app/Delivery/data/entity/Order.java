package com.delivery.app.Delivery.data.entity;

import com.delivery.app.Delivery.data.entity.pk.OrderPk;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity @Table(name="orders")
@Getter
@IdClass(OrderPk.class)
public class Order {

    @Id @OneToOne
    @JoinColumn(name="user_id")
    private User userId;


    @Id @OneToOne
    @JoinColumn(name="cart_id")
    private Cart cartId;

    @CreatedDate
    @Column(name="order_date")
    private LocalDateTime orderDate;

}

package com.delivery.app.Delivery.data.entity;

import com.delivery.app.Delivery.data.entity.enum_string.DeliveryState;
import com.delivery.app.Delivery.data.entity.pk.OrderPk;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

@Entity @Table(name="orders")
@Getter
@NoArgsConstructor
public class Order {

    @Id
    @JoinColumn(name="id")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId //@MapsId 는 @id로 지정한 컬럼에 @OneToOne 이나 @ManyToOne 관계를 매핑시키는 역할
    @JoinColumn(name = "id")
    private Cart cart;

    @Column(name="user_id")
    private Long userId;
    private String state;
    @CreatedDate
    @Column(name="order_date")
    private LocalDateTime orderDate;

    public Order(Long userId, Cart cart, DeliveryState state) {
        this.userId = userId;
        this.cart = cart;

        try{
            this.state = state.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

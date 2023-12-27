package com.delivery.app.Delivery.data.entity;

import com.delivery.app.Delivery.data.entity.enum_string.DeliveryState;
import com.delivery.app.Delivery.data.entity.pk.OrderPk;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

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

    private String state;

    @CreatedDate
    @Column(name="order_date")
    private LocalDateTime orderDate;

    public Order(User userId, Cart cartId, String state) throws Exception {
        this.userId = userId;
        this.cartId = cartId;

        ArrayList<String> stateArray = new ArrayList<>(3);
        stateArray.add(DeliveryState.OnReception.toString());
        stateArray.add(DeliveryState.OnDelivery.toString());
        stateArray.add(DeliveryState.Delivered.toString());

        if(!stateArray.contains(state)) {
            throw new Exception("Wrong Argument on Order.state");
        } else {
            this.state = state;
        }
    }

}

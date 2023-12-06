package com.delivery.app.Delivery.data.entity.pk;

import com.delivery.app.Delivery.data.entity.Cart;
import com.delivery.app.Delivery.data.entity.User;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
public class OrderPk implements Serializable {
    @Column(name="user_id")
    private Long userId;

    @Column(name="cart_id")
    private Long cartId;
}

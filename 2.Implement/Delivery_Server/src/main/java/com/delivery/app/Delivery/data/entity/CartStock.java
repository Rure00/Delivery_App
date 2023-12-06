package com.delivery.app.Delivery.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

@Entity @Table(name = "cart_stocks")
@AllArgsConstructor
@Getter
@IdClass(CartStock.class)
public class CartStock {

    @Id @OneToOne
    @JoinColumn(name="cart_id")
    private Cart cartId;

    @Id @ManyToOne
    @JoinColumn(name="stock_id")
    private Stock stockId;

    @Column(nullable = false)
    private int count;
}

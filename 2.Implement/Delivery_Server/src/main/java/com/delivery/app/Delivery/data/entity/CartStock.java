package com.delivery.app.Delivery.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity @Table(name = "cart_stocks")
@NoArgsConstructor
@Getter
@IdClass(CartStock.class)
public class CartStock {

    @Id @OneToOne
    @JoinColumn(name="cart_id")
    private Cart cart;

    @Id @ManyToOne
    @JoinColumn(name="stock_id", insertable = false, updatable = false)
    private Stock stock;
    @Column(name="stock_id")
    private Long stockId;


    @Column(nullable = false)
    private int count;

    public CartStock(Cart cart, Long stockId, Integer count) {
        this.cart = cart;
        this.stockId = stockId;
        this.count = count;
    }
}

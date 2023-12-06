package com.delivery.app.Delivery.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Getter
@AllArgsConstructor
@Table(name="order_stocks")
@NoArgsConstructor
@IdClass(OrderStocksPK.class)
public class OrderStocks {

    @Id @ManyToOne
    @JoinColumn(name="order_id", referencedColumnName = "id")
    private Order orderId;

    @Id @ManyToOne
    @JoinColumn(name="stock_id", referencedColumnName = "id")
    private Stock stockId;

    @Column(nullable = false)
    private int count;

}

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
class OrderStocksPK implements Serializable {

    @EqualsAndHashCode.Include
    private Long orderId;

    @EqualsAndHashCode.Include
    private Long stockId;

}

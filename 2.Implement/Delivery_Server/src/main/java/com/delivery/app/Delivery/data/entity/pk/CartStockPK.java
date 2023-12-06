package com.delivery.app.Delivery.data.entity.pk;

import com.delivery.app.Delivery.data.entity.Cart;
import com.delivery.app.Delivery.data.entity.Stock;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@AllArgsConstructor
public class CartStockPK implements Serializable {
    @Column(name="cart_id")
    private Long cartId;

    @Column(name="stock_id")
    private Long stockId;
}

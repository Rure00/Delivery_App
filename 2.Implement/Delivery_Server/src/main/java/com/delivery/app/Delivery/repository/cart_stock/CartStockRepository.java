package com.delivery.app.Delivery.repository.cart_stock;

import com.delivery.app.Delivery.data.entity.Cart;
import com.delivery.app.Delivery.data.entity.CartStock;
import com.delivery.app.Delivery.data.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartStockRepository extends JpaRepository<CartStock, Long> {
    List<CartStock> findByCartId(Long cartId);
}

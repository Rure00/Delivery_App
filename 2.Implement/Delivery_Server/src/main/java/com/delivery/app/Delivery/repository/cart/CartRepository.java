package com.delivery.app.Delivery.repository.cart;

import com.delivery.app.Delivery.data.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findById(Long cartId);
    List<Cart> findByUserId(Long userId);
}

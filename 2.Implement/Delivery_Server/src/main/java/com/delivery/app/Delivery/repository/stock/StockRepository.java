package com.delivery.app.Delivery.repository.stock;

import com.delivery.app.Delivery.data.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}

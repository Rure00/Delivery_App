package com.delivery.app.Delivery.dao;

import com.delivery.app.Delivery.data.dto.request.UserIdDto;
import com.delivery.app.Delivery.data.entity.Order;

import java.util.List;

public interface OrderDAO {
    List<Order> getUserOrders(UserIdDto userIdDto);

    Order getOrder(Long id);
}

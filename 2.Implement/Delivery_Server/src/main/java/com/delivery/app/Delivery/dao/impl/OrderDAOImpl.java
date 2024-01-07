package com.delivery.app.Delivery.dao.impl;

import com.delivery.app.Delivery.dao.OrderDAO;
import com.delivery.app.Delivery.data.dto.request.UserIdDto;
import com.delivery.app.Delivery.data.entity.Order;
import com.delivery.app.Delivery.repository.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrderDAOImpl implements OrderDAO  {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderDAOImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order getOrder(Long id) {
        Optional<Order> selected = orderRepository.findById(id);

        if(selected.isPresent()) {
            return selected.get();
        } else {
            Exception e = new Exception("Not Found");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Order> getUserOrders(UserIdDto userIdDto) {
        return orderRepository.findByUserId(userIdDto.getUserId());
    }


}

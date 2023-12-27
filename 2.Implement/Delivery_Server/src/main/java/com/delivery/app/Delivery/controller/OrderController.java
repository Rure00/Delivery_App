package com.delivery.app.Delivery.controller;

import com.delivery.app.Delivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderService service;

    @Autowired
    public OrderController(OrderService service) { this.service = service; }

    @PostMapping("/get")
    public void getMyOrders() {

    }

    @PostMapping("/detail")
    public void getOrderDetail() {

    }
}

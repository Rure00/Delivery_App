package com.delivery.app.Delivery.controller;

import com.delivery.app.Delivery.service.CartService;
import com.delivery.app.Delivery.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    private CartService service;

    @Autowired
    public CartController(CartService service) {this.service = service;}

    @PostMapping("/save")
    public void saveCart() {

    }

    @PostMapping("/get")
    public void getCarts() {

    }

    @PostMapping("/detail")
    public void getCartDetail() {

    }
}

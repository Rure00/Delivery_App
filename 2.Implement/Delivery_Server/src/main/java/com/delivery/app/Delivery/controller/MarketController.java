package com.delivery.app.Delivery.controller;

import com.delivery.app.Delivery.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/market")
public class MarketController {
    private final MarketService service;

    @Autowired
    public MarketController(MarketService service) {this.service = service;}

    @PostMapping("/get")
    public void getMarketInfo() {

    }

    @PostMapping("/near")
    public void getNearMarket() {

    }

    @PostMapping("/items")
    public void getMarketItems() {

    }
}

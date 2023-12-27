package com.delivery.app.Delivery.controller;

import com.delivery.app.Delivery.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {
    private StockService service;

    @Autowired
    public StockController(StockService service) { this.service = service; }

    @PostMapping("get")
    public void getStockDetail() {

    }

}

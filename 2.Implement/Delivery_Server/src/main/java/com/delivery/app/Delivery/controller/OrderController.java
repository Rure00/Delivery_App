package com.delivery.app.Delivery.controller;

import com.delivery.app.Delivery.data.dto.request.OrderIdDto;
import com.delivery.app.Delivery.data.dto.request.UserIdDto;
import com.delivery.app.Delivery.data.dto.response.ResponseResult;
import com.delivery.app.Delivery.data.dto.response.order.GetOrdersResponseDto;
import com.delivery.app.Delivery.data.dto.response.order.OrderDetailResponseDto;
import com.delivery.app.Delivery.data.dto.response.review.GetMarketReviewsResponseDto;
import com.delivery.app.Delivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderService service;

    @Autowired
    public OrderController(OrderService service) { this.service = service; }

    @PostMapping("/get")
    public ResponseEntity<ResponseResult> getUserOrders(@RequestBody UserIdDto userIdDto) {
        GetOrdersResponseDto orders = service.getUserOrders(userIdDto);
        ResponseResult result = new ResponseResult();

        if(!orders.isEmpty()) {
            result.setResponseDto(orders);
            result.setFlag(true);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            result.setFlag(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }

    @PostMapping("/detail")
    public ResponseEntity<ResponseResult> getOrderDetail(@RequestBody OrderIdDto orderIdDto) {
        OrderDetailResponseDto detail = service.getOrderDetail(orderIdDto);
        ResponseResult result = new ResponseResult();

        if(!detail.isEmpty()) {
            result.setResponseDto(detail);
            result.setFlag(true);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            result.setFlag(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }

    @PostMapping("/give-order")
    public void giveOrder() {

    }
}

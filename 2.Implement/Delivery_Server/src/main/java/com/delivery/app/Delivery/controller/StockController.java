package com.delivery.app.Delivery.controller;

import com.delivery.app.Delivery.data.dto.request.StockIdDto;
import com.delivery.app.Delivery.data.dto.request.stock.StockDto;
import com.delivery.app.Delivery.data.dto.response.ResponseResult;
import com.delivery.app.Delivery.data.dto.response.review.GetMarketReviewsResponseDto;
import com.delivery.app.Delivery.data.dto.response.stock.StockDetailResponseDto;
import com.delivery.app.Delivery.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {
    private StockService service;

    @Autowired
    public StockController(StockService service) { this.service = service; }

    @PostMapping("/add")
    public ResponseEntity<ResponseResult> addStock(@RequestBody StockDto stockDto) {
        boolean isSuccess = service.addNewStock(stockDto);
        ResponseResult result = new ResponseResult();

        if(isSuccess) {
            result.setSuccess(true);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } else {
            result.setSuccess(false);
            return ResponseEntity.status(HttpStatus.IM_USED).body(result);
        }
    }

    @PostMapping("get")
    public ResponseEntity<ResponseResult> getStockDetail(@RequestBody StockIdDto stockIdDto) {
        StockDetailResponseDto detail = service.getStockDetail(stockIdDto);
        ResponseResult result = new ResponseResult();

        if(!detail.isEmpty()) {
            result.setResponseDto(detail);
            result.setSuccess(true);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            result.setSuccess(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }

}

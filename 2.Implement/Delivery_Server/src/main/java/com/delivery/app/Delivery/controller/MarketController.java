package com.delivery.app.Delivery.controller;

import com.delivery.app.Delivery.data.dto.market.MarketInfoDto;
import com.delivery.app.Delivery.data.dto.market.NearMarketsDto;
import com.delivery.app.Delivery.data.dto.response.MarketItemsResponseDto;
import com.delivery.app.Delivery.data.dto.response.MarketResponseDto;
import com.delivery.app.Delivery.data.dto.response.NearMarketsResponseDto;
import com.delivery.app.Delivery.data.dto.response.ResponseResult;
import com.delivery.app.Delivery.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/market")
public class MarketController {
    private final MarketService service;

    @Autowired
    public MarketController(MarketService service) {this.service = service;}

    @PostMapping("/get")
    public ResponseEntity<ResponseResult> getMarketInfo(@RequestBody MarketInfoDto marketInfoDto) {

        MarketResponseDto marketResponseDto = service.getMarketInfo(marketInfoDto);
        ResponseResult responseResult = new ResponseResult();
        responseResult.setResponseDto(marketResponseDto);

        if(marketResponseDto != null) {
            responseResult.setSuccess(true);
            return ResponseEntity.status(HttpStatus.OK).body(responseResult);
        } else {
            responseResult.setSuccess(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseResult);
        }
    }

    @PostMapping("/near")
    public ResponseEntity<ResponseResult> getNearMarket(@RequestBody NearMarketsDto nearMarketsDto) {

        NearMarketsResponseDto list = service.getNearMarkets(nearMarketsDto);
        ResponseResult responseResult = new ResponseResult();
        responseResult.setResponseDto(list);

        if(!list.getNearMarketsList().isEmpty()) {
            responseResult.setSuccess(true);
            return ResponseEntity.status(HttpStatus.OK).body(responseResult);
        } else {
            responseResult.setSuccess(true);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseResult);
        }

    }

    @PostMapping("/items")
    public ResponseEntity<ResponseResult> getMarketItems(@RequestBody MarketInfoDto marketInfoDto) {

        MarketItemsResponseDto list = service.getMarketItems(marketInfoDto);
        ResponseResult responseResult = new ResponseResult();
        responseResult.setResponseDto(list);

        if(!list.getStockList().isEmpty()) {
            responseResult.setSuccess(true);
            return ResponseEntity.status(HttpStatus.OK).body(responseResult);
        } else {
            responseResult.setSuccess(true);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseResult);
        }

    }
}

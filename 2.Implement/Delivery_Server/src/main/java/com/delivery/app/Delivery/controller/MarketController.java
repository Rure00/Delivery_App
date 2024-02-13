package com.delivery.app.Delivery.controller;

import com.delivery.app.Delivery.data.dto.request.StockIdDto;
import com.delivery.app.Delivery.data.dto.request.market.AddItemDto;
import com.delivery.app.Delivery.data.dto.request.market.MarketInfoDto;
import com.delivery.app.Delivery.data.dto.request.market.MarketSignUpDto;
import com.delivery.app.Delivery.data.dto.request.market.NearMarketsDto;
import com.delivery.app.Delivery.data.dto.response.market.*;
import com.delivery.app.Delivery.data.dto.response.ResponseResult;
import com.delivery.app.Delivery.data.dto.response.user.SignUpResponseDto;
import com.delivery.app.Delivery.data.my_enum.SignUpCode;
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

    @PostMapping("/signup")
    public ResponseEntity<ResponseResult> createMarket(@RequestBody MarketSignUpDto marketSignUpDto) {
        MarketSignUpResponseDto responseDto = new MarketSignUpResponseDto();
        ResponseResult result = new ResponseResult();
        result.setResponseDto(responseDto);

        if(marketSignUpDto.hasNull()) {
            responseDto.setDescription("잘못된 요청");
            result.setFlag(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

        SignUpCode code = service.trySignUp(marketSignUpDto);

        switch (code) {
            case SUCCESS -> {
                responseDto.setDescription("회원 가입 성공");
                result.setFlag(true);
                return ResponseEntity.status(HttpStatus.CREATED).body(result);
            }
            case DUPLICATED_ID -> {
                responseDto.setDescription("ID 중복");
                result.setFlag(false);
                return ResponseEntity.status(HttpStatus.IM_USED).body(result);
            }
            default -> {
                responseDto.setDescription("회원 가입 실패");
                result.setFlag(false);
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(result);
            }
        }
    }

    @PostMapping("/get")
    public ResponseEntity<ResponseResult> getMarketInfo(@RequestBody MarketInfoDto marketInfoDto) {
        System.out.println("Market Id is " + marketInfoDto.getMarketId());
        MarketResponseDto responseDto = service.getMarketInfo(marketInfoDto);
        ResponseResult responseResult = new ResponseResult();



        if(responseDto != null) {
            responseResult.setResponseDto(responseDto);
            responseResult.setFlag(true);
            return ResponseEntity.status(HttpStatus.OK).body(responseResult);
        } else {
            responseResult.setFlag(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseResult);
        }
    }

    @PostMapping("/near")
    public ResponseEntity<ResponseResult> getNearMarket(@RequestBody NearMarketsDto nearMarketsDto) {

        NearMarketsResponseDto responseDto = service.getNearMarkets(nearMarketsDto);
        ResponseResult responseResult = new ResponseResult();
        responseResult.setResponseDto(responseDto);

        if(!responseDto.getId().isEmpty()) {
            responseResult.setFlag(true);
            System.out.println("NearMarket is Called...) result has " + responseDto.getAddress().size());
            System.out.println("Latitude: " + responseDto.getLatitude().get(0) + " Longitude: " + responseDto.getLongitude().get(0));
            return ResponseEntity.status(HttpStatus.OK).body(responseResult);
        } else {
            responseResult.setFlag(false);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseResult);
        }

    }

    @PostMapping("/items")
    public ResponseEntity<ResponseResult> getMarketItems(@RequestBody MarketInfoDto marketInfoDto) {

        MarketItemsResponseDto list = service.getMarketItems(marketInfoDto);
        ResponseResult responseResult = new ResponseResult();
        responseResult.setResponseDto(list);

        if(!list.getStockList().isEmpty()) {
            responseResult.setFlag(true);
            return ResponseEntity.status(HttpStatus.OK).body(responseResult);
        } else {
            responseResult.setFlag(false);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseResult);
        }

    }

    @PostMapping("/add/item")
    public ResponseEntity<ResponseResult> addItem(@RequestBody AddItemDto addItemDto) {

        AddItemResponseDto responseDto = service.addItem(addItemDto);
        ResponseResult responseResult = new ResponseResult();
        responseResult.setResponseDto(responseDto);

        if(responseDto != null) {
            responseResult.setFlag(true);
            return ResponseEntity.status(HttpStatus.OK).body(responseResult);
        } else {
            responseResult.setFlag(false);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseResult);
        }

    }
}

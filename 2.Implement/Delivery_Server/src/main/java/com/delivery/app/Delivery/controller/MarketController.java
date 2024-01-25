package com.delivery.app.Delivery.controller;

import com.delivery.app.Delivery.data.dto.request.market.MarketInfoDto;
import com.delivery.app.Delivery.data.dto.request.market.MarketSignUpDto;
import com.delivery.app.Delivery.data.dto.request.market.NearMarketsDto;
import com.delivery.app.Delivery.data.dto.response.market.MarketItemsResponseDto;
import com.delivery.app.Delivery.data.dto.response.market.MarketResponseDto;
import com.delivery.app.Delivery.data.dto.response.market.MarketSignUpResponseDto;
import com.delivery.app.Delivery.data.dto.response.market.NearMarketsResponseDto;
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

        MarketResponseDto marketResponseDto = service.getMarketInfo(marketInfoDto);
        ResponseResult responseResult = new ResponseResult();


        if(marketResponseDto != null) {
            responseResult.setResponseDto(marketResponseDto);
            responseResult.setFlag(true);
            return ResponseEntity.status(HttpStatus.OK).body(responseResult);
        } else {
            responseResult.setFlag(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseResult);
        }
    }

    @PostMapping("/near")
    public ResponseEntity<ResponseResult> getNearMarket(@RequestBody NearMarketsDto nearMarketsDto) {

        NearMarketsResponseDto list = service.getNearMarkets(nearMarketsDto);
        ResponseResult responseResult = new ResponseResult();
        responseResult.setResponseDto(list);

        if(!list.getNearMarketsList().isEmpty()) {
            responseResult.setFlag(true);
            return ResponseEntity.status(HttpStatus.OK).body(responseResult);
        } else {
            responseResult.setFlag(true);
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
            responseResult.setFlag(true);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseResult);
        }

    }
}

package com.delivery.app.Delivery.controller;

import com.delivery.app.Delivery.data.dto.request.CartIdDto;
import com.delivery.app.Delivery.data.dto.request.UserIdDto;
import com.delivery.app.Delivery.data.dto.request.cart.SaveCartDto;
import com.delivery.app.Delivery.data.dto.response.ResponseResult;
import com.delivery.app.Delivery.data.dto.response.cart.CartDetailResponseDto;
import com.delivery.app.Delivery.data.dto.response.cart.GetCartsResponseDto;
import com.delivery.app.Delivery.data.dto.response.cart.SaveCartResponseDto;
import com.delivery.app.Delivery.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    private CartService service;

    @Autowired
    public CartController(CartService service) {this.service = service;}

    @PostMapping("/save")
    public ResponseEntity<ResponseResult> saveCart(@RequestBody SaveCartDto saveCartDto) {

        ResponseResult result = new ResponseResult();
        try {
            Long id = service.saveCart(saveCartDto);
            result.setFlag(true);
            result.setResponseDto(new SaveCartResponseDto(id));
        } catch (Exception e) {
            result.setFlag(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/get")
    public ResponseEntity<ResponseResult> getCarts(@RequestBody UserIdDto userIdDto) {
        ResponseResult result = new ResponseResult();
        GetCartsResponseDto getCartsResponseDto = service.getUserCarts(userIdDto);

        try {
            result.setResponseDto(getCartsResponseDto);
            result.setFlag(true);
        } catch (Exception e) {
            result.setFlag(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/detail")
    public ResponseEntity<ResponseResult> getCartDetail(@RequestBody CartIdDto cartIdDto) {

        ResponseResult result = new ResponseResult();
        CartDetailResponseDto cartDetailResponseDto = service.getCartDetail(cartIdDto);

        result.setResponseDto(cartDetailResponseDto);

        if(cartDetailResponseDto != null) {
            result.setFlag(true);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            result.setFlag(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

    }

    @PostMapping("/remove")
    public ResponseEntity<ResponseResult> removeCart(@RequestBody CartIdDto cartIdDto) {
        ResponseResult result = new ResponseResult();

        try {
            service.removeCart(cartIdDto);
            result.setFlag(true);
        } catch (Exception e) {
            result.setFlag(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}

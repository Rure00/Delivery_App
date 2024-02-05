package com.delivery.app.Delivery.service;

import com.delivery.app.Delivery.data.dto.request.CartIdDto;
import com.delivery.app.Delivery.data.dto.request.UserIdDto;
import com.delivery.app.Delivery.data.dto.request.cart.SaveCartDto;
import com.delivery.app.Delivery.data.dto.response.cart.CartDetailResponseDto;
import com.delivery.app.Delivery.data.dto.response.cart.GetCartsResponseDto;
import org.springframework.stereotype.Service;


public interface CartService {
    Long saveCart(SaveCartDto saveCartDto);

    GetCartsResponseDto getUserCarts(UserIdDto userIdDto);

    CartDetailResponseDto getCartDetail(CartIdDto cartIdDto);

    void removeCart(CartIdDto cartIdDto);
}

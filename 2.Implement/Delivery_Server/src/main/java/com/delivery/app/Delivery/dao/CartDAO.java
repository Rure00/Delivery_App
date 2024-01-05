package com.delivery.app.Delivery.dao;

import com.delivery.app.Delivery.data.dto.request.CartIdDto;
import com.delivery.app.Delivery.data.dto.request.UserIdDto;
import com.delivery.app.Delivery.data.dto.request.cart.SaveCartDto;
import com.delivery.app.Delivery.data.dto.response.cart.CartDetailResponseDto;
import com.delivery.app.Delivery.data.dto.response.cart.GetCartsResponseDto;

public interface CartDAO {
    void save(SaveCartDto saveCartDto);

    GetCartsResponseDto getUserCarts(UserIdDto userIdDto);

    CartDetailResponseDto getCartDetail(CartIdDto cartIdDto);

    void removeCart(CartIdDto cartIdDto);
}

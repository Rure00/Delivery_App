package com.delivery.app.Delivery.dao;

import com.delivery.app.Delivery.data.dto.request.CartIdDto;
import com.delivery.app.Delivery.data.dto.request.UserIdDto;
import com.delivery.app.Delivery.data.dto.request.cart.SaveCartDto;
import com.delivery.app.Delivery.data.dto.response.cart.CartDetailResponseDto;
import com.delivery.app.Delivery.data.dto.response.cart.GetCartsResponseDto;
import com.delivery.app.Delivery.data.entity.Cart;
import com.delivery.app.Delivery.data.entity.CartStock;
import com.delivery.app.Delivery.data.entity.Order;

import java.util.List;

public interface CartDAO {
    Long save(SaveCartDto saveCartDto);

    GetCartsResponseDto getUserCarts(UserIdDto userIdDto);

    CartDetailResponseDto getCartDetail(CartIdDto cartIdDto);

    void removeCart(CartIdDto cartIdDto);

    Cart getCart(Long cartId);

    List<CartStock> getItemsInCart(Long id);
}

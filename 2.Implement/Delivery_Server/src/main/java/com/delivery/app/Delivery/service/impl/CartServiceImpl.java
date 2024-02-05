package com.delivery.app.Delivery.service.impl;

import com.delivery.app.Delivery.dao.CartDAO;
import com.delivery.app.Delivery.dao.MarketDAO;
import com.delivery.app.Delivery.dao.UserDAO;
import com.delivery.app.Delivery.data.dto.request.CartIdDto;
import com.delivery.app.Delivery.data.dto.request.UserIdDto;
import com.delivery.app.Delivery.data.dto.request.cart.SaveCartDto;
import com.delivery.app.Delivery.data.dto.response.cart.CartDetailResponseDto;
import com.delivery.app.Delivery.data.dto.response.cart.GetCartsResponseDto;
import com.delivery.app.Delivery.data.entity.Stock;
import com.delivery.app.Delivery.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CartServiceImpl implements CartService {

    private final CartDAO cartDAO;

    @Autowired
    public CartServiceImpl(CartDAO cartDAO) { this.cartDAO = cartDAO; }

    @Override
    public Long saveCart(SaveCartDto saveCartDto) {
        return cartDAO.save(saveCartDto);
    }

    @Override
    public GetCartsResponseDto getUserCarts(UserIdDto userIdDto) {
        GetCartsResponseDto result = cartDAO.getUserCarts(userIdDto);
        return result.isEmpty() ? result : null;
    }

    @Override
    public CartDetailResponseDto getCartDetail(CartIdDto cartIdDto) {
        CartDetailResponseDto result = cartDAO.getCartDetail(cartIdDto);
        return result.isEmpty() ? result : null;
    }

    @Override
    public void removeCart(CartIdDto cartIdDto) {
        cartDAO.removeCart(cartIdDto);
    }
}

package com.delivery.app.Delivery.dao.impl;

import com.delivery.app.Delivery.dao.CartDAO;
import com.delivery.app.Delivery.data.dto.request.CartIdDto;
import com.delivery.app.Delivery.data.dto.request.UserIdDto;
import com.delivery.app.Delivery.data.dto.request.cart.SaveCartDto;
import com.delivery.app.Delivery.data.dto.response.cart.CartDetailResponseDto;
import com.delivery.app.Delivery.data.dto.response.cart.GetCartsResponseDto;
import com.delivery.app.Delivery.data.entity.Cart;
import com.delivery.app.Delivery.data.entity.CartStock;
import com.delivery.app.Delivery.data.entity.Order;
import com.delivery.app.Delivery.data.entity.Stock;
import com.delivery.app.Delivery.repository.cart.CartRepository;
import com.delivery.app.Delivery.repository.cart_stock.CartStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CartDAOImpl implements CartDAO {

    private final CartRepository cartRepository;
    private final CartStockRepository cartStockRepository;

    @Autowired
    public CartDAOImpl(CartRepository cartRepository, CartStockRepository cartStockRepository) {
        this.cartRepository = cartRepository;
        this.cartStockRepository = cartStockRepository;
    }

    @Override
    public Long save(SaveCartDto saveCartDto) {
        Cart cartInstance = cartRepository.save(
                new Cart(
                        saveCartDto.getUserId(), saveCartDto.getMarketId(),
                        saveCartDto.getMarketName(), saveCartDto.getCost()
                )
        );

        int size = saveCartDto.getItemIdList().size();
        ArrayList<Long> stockList = saveCartDto.getItemIdList();
        ArrayList<Integer> countList = saveCartDto.getItemNumList();
        for(int i=0; i< size; i++) {
            Long stockId = stockList.get(i);
            Integer count = countList.get(i);
            CartStock stockInCart = new CartStock(
                cartInstance, stockId, count
            );

            cartStockRepository.save(stockInCart);
        }

        return cartInstance.getId();
    }

    @Override
    public GetCartsResponseDto getUserCarts(UserIdDto userIdDto) {
        Long userId = userIdDto.getUserId();
        List<Cart> cartList = cartRepository.findByUserId(userId);

        GetCartsResponseDto result = new GetCartsResponseDto();
        for(Cart cart: cartList) {
            result.addElement(cart.getId(), cart.getMarket().getName(), cart.getCost());
        }
        return result;
    }

    @Override
    public CartDetailResponseDto getCartDetail(CartIdDto cartIdDto) {
        CartDetailResponseDto result = new CartDetailResponseDto();
        Long cartId = cartIdDto.getCartId();
        Optional<Cart> selectedCart = cartRepository.findById(cartId);

        if(selectedCart.isPresent()) {
            List<CartStock> list = cartStockRepository.findByCartId(cartId);
            for(CartStock cartStock: list) {
                result.addElement(cartStock.getStock(), cartStock.getCount());
            }
        }

        return result;
    }

    @Override
    public void removeCart(CartIdDto cartIdDto) {
        Long cartId = cartIdDto.getCartId();
        Optional<Cart> selectedCart = cartRepository.findById(cartId);

        if(selectedCart.isPresent()) {
            cartRepository.deleteById(cartId);
        } else {
            System.err.println("CartDaoImpl::removeCart) There is no such Cart that Id is " +cartId);
        }


    }

    @Override
    public Cart getCart(Long orderId) {
        Optional<Cart> selected = cartRepository.findById(orderId);

        if(selected.isPresent()) {
            return selected.get();
        } else {
            Exception e = new Exception("Id is not found");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<CartStock> getItemsInCart(Long id) {
        return cartStockRepository.findByCartId(id);
    }
}

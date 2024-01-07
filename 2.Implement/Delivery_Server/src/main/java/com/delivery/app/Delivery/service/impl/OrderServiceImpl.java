package com.delivery.app.Delivery.service.impl;

import com.delivery.app.Delivery.dao.CartDAO;
import com.delivery.app.Delivery.dao.OrderDAO;
import com.delivery.app.Delivery.dao.StockDAO;
import com.delivery.app.Delivery.data.dto.request.OrderIdDto;
import com.delivery.app.Delivery.data.dto.request.UserIdDto;
import com.delivery.app.Delivery.data.dto.response.order.GetOrdersResponseDto;
import com.delivery.app.Delivery.data.dto.response.order.OrderDetailResponseDto;
import com.delivery.app.Delivery.data.entity.Cart;
import com.delivery.app.Delivery.data.entity.CartStock;
import com.delivery.app.Delivery.data.entity.Order;
import com.delivery.app.Delivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;
    private final CartDAO cartDAO;
    private final StockDAO stockDAO;

    @Autowired
    public OrderServiceImpl(OrderDAO orderDAO, CartDAO cartDAO, StockDAO stockDAO) {
        this.orderDAO = orderDAO;
        this.cartDAO = cartDAO;
        this.stockDAO = stockDAO;
    }

    @Override
    public GetOrdersResponseDto getUserOrders(UserIdDto userIdDto) {
        GetOrdersResponseDto result = new GetOrdersResponseDto();
        List<Order> orders = orderDAO.getUserOrders(userIdDto);
        for(Order order: orders) {
            Cart cart = null;
            try {
                cart = cartDAO.getCart(order.getId());

                Long id = cart.getId();
                String marketName = cart.getMarketName();
                Integer cost = cart.getCost();
                LocalDateTime date = order.getOrderDate();

                result.addElement(id, marketName, cost, date);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    @Override
    public OrderDetailResponseDto getOrderDetail(OrderIdDto orderIdDto) {
        Long id = orderIdDto.getOrderId();
        Order order = orderDAO.getOrder(id);

        List<CartStock> itemList = cartDAO.getItemsInCart(id);
        OrderDetailResponseDto result = new OrderDetailResponseDto(order.getState(), order.getOrderDate());

        for(CartStock item: itemList) {
            Long itemId = item.getStockId();
            Integer count = item.getCount();
            Integer price = stockDAO.getStock(itemId).getPrice();

            result.addElement(itemId, count, price);
        }


        return result;
    }
}

package com.delivery.app.Delivery.service;

import com.delivery.app.Delivery.data.dto.request.CartIdDto;
import com.delivery.app.Delivery.data.dto.request.OrderIdDto;
import com.delivery.app.Delivery.data.dto.request.UserIdDto;
import com.delivery.app.Delivery.data.dto.response.order.GetOrdersResponseDto;
import com.delivery.app.Delivery.data.dto.response.order.OrderDetailResponseDto;

public interface OrderService {
    GetOrdersResponseDto getUserOrders(UserIdDto userIdDto);

    OrderDetailResponseDto getOrderDetail(OrderIdDto orderIdDto);
}

package com.delivery.app.Delivery.data.dto.request.market;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddItemDto {
    Long marketId;
    Long stockId;
}

package com.delivery.app.Delivery.data.dto.request.stock;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class StockDto {
    private Long stockId;
    private String name;
    private Integer price;
    private LocalDateTime releasedDate;
    private String manufacturer;
    private Integer weight;
}

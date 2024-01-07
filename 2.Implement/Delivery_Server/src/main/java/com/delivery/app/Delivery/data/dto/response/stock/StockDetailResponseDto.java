package com.delivery.app.Delivery.data.dto.response.stock;

import com.delivery.app.Delivery.data.dto.response.BaseResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class StockDetailResponseDto implements BaseResponseDto {
    private Long id;
    private String name;
    private Integer price;
    private LocalDateTime releasedDate;
    private String manufacturer;
    private Integer weight;

    public Boolean isEmpty() {
        return (id == null);
    }
    public void set(Long id, String name, Integer price, LocalDateTime releasedDate, String manufacturer, Integer weight) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.releasedDate = releasedDate;
        this.manufacturer = manufacturer;
        this.weight = weight;
    }
}

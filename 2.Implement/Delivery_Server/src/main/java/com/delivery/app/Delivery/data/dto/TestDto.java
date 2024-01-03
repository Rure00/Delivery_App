package com.delivery.app.Delivery.data.dto;

import com.delivery.app.Delivery.data.dto.user.LoginDto;
import lombok.Getter;

import java.util.List;

@Getter
public class TestDto {
    String name;
    List<LoginDto> loginDtoList;
}

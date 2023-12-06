package com.delivery.app.Delivery.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponseDto {
    private int id;
    private String name;
    private String nickname;
    private String loginId;
    private String loginPwd;
    private String phoneNumber;
    private String gender;
    private String address;
}

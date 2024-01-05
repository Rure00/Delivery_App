package com.delivery.app.Delivery.data.dto.request.user;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpDto {
    private String name;
    private String nickname;
    private String loginId;
    private String loginPwd;
    private String phoneNumber;
    private String gender;
    private String address;
}

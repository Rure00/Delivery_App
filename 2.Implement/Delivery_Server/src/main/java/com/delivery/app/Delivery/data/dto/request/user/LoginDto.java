package com.delivery.app.Delivery.data.dto.request.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginDto {
    private String loginId;
    private String loginPwd;
}

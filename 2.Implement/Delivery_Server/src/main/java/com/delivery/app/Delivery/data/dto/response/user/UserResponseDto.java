package com.delivery.app.Delivery.data.dto.response.user;

import com.delivery.app.Delivery.data.dto.response.BaseResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponseDto implements BaseResponseDto {
    private Long id;
    private String name;
    private String nickname;
    private String loginId;
    private String loginPwd;
    private String phoneNumber;
    private String gender;
    private String address;
    private String createAt;
}

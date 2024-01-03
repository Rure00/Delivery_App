package com.delivery.app.Delivery.service;

import com.delivery.app.Delivery.data.dto.user.LoginDto;
import com.delivery.app.Delivery.data.dto.user.SignUpDto;
import com.delivery.app.Delivery.data.dto.response.UserResponseDto;
import com.delivery.app.Delivery.data.my_enum.SignUpCode;

public interface UserService {
    UserResponseDto tryLogin(LoginDto loginDto);

    SignUpCode trySignUp(SignUpDto signUpDto);
}

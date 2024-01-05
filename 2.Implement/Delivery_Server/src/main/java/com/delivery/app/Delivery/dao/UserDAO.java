package com.delivery.app.Delivery.dao;

import com.delivery.app.Delivery.data.dto.request.user.LoginDto;
import com.delivery.app.Delivery.data.dto.request.user.SignUpDto;
import com.delivery.app.Delivery.data.entity.User;
import com.delivery.app.Delivery.data.my_enum.SignUpCode;

public interface UserDAO {
    User getUser(LoginDto loginDto);

    SignUpCode insertUser(SignUpDto signUpDto);
}

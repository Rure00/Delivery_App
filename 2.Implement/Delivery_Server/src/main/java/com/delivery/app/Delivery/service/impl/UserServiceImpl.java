package com.delivery.app.Delivery.service.impl;

import com.delivery.app.Delivery.dao.UserDAO;
import com.delivery.app.Delivery.data.dto.request.user.LoginDto;
import com.delivery.app.Delivery.data.dto.request.user.SignUpDto;
import com.delivery.app.Delivery.data.dto.response.user.UserResponseDto;
import com.delivery.app.Delivery.data.entity.User;
import com.delivery.app.Delivery.data.my_enum.SignUpCode;
import com.delivery.app.Delivery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {this.userDAO = userDAO;}

    @Override
    public UserResponseDto tryLogin(LoginDto loginDto) {
        User user = userDAO.getUser(loginDto);

        if(user==null) return null;

        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getNickname(),
                user.getLoginID(),
                user.getLoginPwd(),
                user.getPhoneNumber(),
                user.getGender(),
                user.getAddress(),
                user.getCreatedAt().toString()
        );
    }

    @Override
    public SignUpCode trySignUp(SignUpDto signUpDto) {



        return userDAO.insertUser(signUpDto);
    }
}

package com.delivery.app.Delivery.dao.impl;

import com.delivery.app.Delivery.dao.UserDAO;
import com.delivery.app.Delivery.data.dto.LoginDto;
import com.delivery.app.Delivery.data.dto.SignUpDto;
import com.delivery.app.Delivery.data.entity.User;
import com.delivery.app.Delivery.data.my_enum.SignUpCode;
import com.delivery.app.Delivery.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserDAOImpl implements UserDAO {

    private final UserRepository userRepository;

    @Autowired
    public UserDAOImpl(UserRepository userRepository) { this.userRepository = userRepository; }


    @Override
    public User getUser(LoginDto loginDto) {
        String id = loginDto.getLoginId();
        String pwd = loginDto.getLoginPwd();

        return userRepository.getUser(id, pwd);
    }

    @Override
    public SignUpCode insertUser(SignUpDto signUpDto) {
        String id = signUpDto.getLoginId();

        System.out.println(signUpDto.getLoginId());

        Boolean isDuplicated = userRepository.isDuplicated(id);

        if(!isDuplicated) {
            User newUser = new User(
                    0L,
                    signUpDto.getName(),
                    signUpDto.getNickname(),
                    signUpDto.getLoginId(),
                    signUpDto.getLoginPwd(),
                    signUpDto.getPhoneNumber(),
                    signUpDto.getGender(),
                    signUpDto.getAddress(),
                    LocalDateTime.now()
            );

            userRepository.save(newUser);

            return SignUpCode.SUCCESS;
        } else
            return SignUpCode.DUPLICATE_ID;

    }
}

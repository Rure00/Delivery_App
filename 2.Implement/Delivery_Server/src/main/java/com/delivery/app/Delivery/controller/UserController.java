package com.delivery.app.Delivery.controller;

import com.delivery.app.Delivery.data.dto.LoginDto;
import com.delivery.app.Delivery.data.dto.SignUpDto;
import com.delivery.app.Delivery.data.dto.SignUpResponseDto;
import com.delivery.app.Delivery.data.dto.UserResponseDto;
import com.delivery.app.Delivery.data.my_enum.SignUpCode;
import com.delivery.app.Delivery.service.UserService;
import com.sun.net.httpserver.HttpsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {this.service = service;}

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> tryLogin(@RequestBody LoginDto loginDto) {
        UserResponseDto userResponseDto = service.tryLogin(loginDto);

        if(userResponseDto!=null)
            return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> trySignUp(@RequestBody SignUpDto signUpDto) {
        SignUpCode result = service.trySignUp(signUpDto);

        SignUpResponseDto response = switch (result) {
            case SUCCESS -> new SignUpResponseDto(true, "회원 가입 성공");
            case DUPLICATE_ID -> new SignUpResponseDto(false, "동일한 ID가 존재합니다.");
            case FAIL -> new SignUpResponseDto(false, "회원 가입 실패");
        };

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/test")
    public String testFunction() {
        return "Hello it's a test!";
    }



}

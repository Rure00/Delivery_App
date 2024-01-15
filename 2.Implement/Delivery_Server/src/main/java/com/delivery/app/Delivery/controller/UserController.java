package com.delivery.app.Delivery.controller;

import com.delivery.app.Delivery.data.dto.request.user.LoginDto;
import com.delivery.app.Delivery.data.dto.request.user.SignUpDto;
import com.delivery.app.Delivery.data.dto.response.ResponseResult;
import com.delivery.app.Delivery.data.dto.response.user.SignUpResponseDto;
import com.delivery.app.Delivery.data.dto.response.user.UserResponseDto;
import com.delivery.app.Delivery.data.my_enum.SignUpCode;
import com.delivery.app.Delivery.service.UserService;
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
    public ResponseEntity<ResponseResult> tryLogin(@RequestBody LoginDto loginDto) {

        UserResponseDto userResponseDto = service.tryLogin(loginDto);

        ResponseResult responseResult = new ResponseResult();
        responseResult.setResponseDto(userResponseDto);

        if(userResponseDto!=null) {
            responseResult.setSuccess(true);
            return ResponseEntity.status(HttpStatus.OK).body(responseResult);
        }
        else {
            responseResult.setSuccess(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseResult);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseResult> trySignUp(@RequestBody SignUpDto signUpDto) {

        SignUpResponseDto signUpResponseDto = new SignUpResponseDto();
        ResponseResult result = new ResponseResult();
        result.setResponseDto(signUpResponseDto);

        if(signUpDto.hasNull()) {
            signUpResponseDto.setDescription("잘못된 요청");
            result.setSuccess(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

        SignUpCode code = service.trySignUp(signUpDto);

        switch (code) {
            case SUCCESS -> {
                signUpResponseDto.setDescription("회원 가입 성공");
                result.setSuccess(true);
                return ResponseEntity.status(HttpStatus.CREATED).body(result);
            }
            case DUPLICATED_ID -> {
                signUpResponseDto.setDescription("ID 중복");
                result.setSuccess(false);
                return ResponseEntity.status(HttpStatus.IM_USED).body(result);
            }
            default -> {
                signUpResponseDto.setDescription("회원 가입 실패");
                result.setSuccess(false);
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(result);
            }
        }
    }



}

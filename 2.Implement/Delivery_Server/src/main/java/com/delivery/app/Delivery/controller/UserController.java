package com.delivery.app.Delivery.controller;

import com.delivery.app.Delivery.FeedbackMessages;
import com.delivery.app.Delivery.data.dto.request.user.LoginDto;
import com.delivery.app.Delivery.data.dto.request.user.SignUpDto;
import com.delivery.app.Delivery.data.dto.response.ErrorResponseDto;
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
        ResponseResult responseResult = new ResponseResult();
        UserResponseDto userResponseDto = service.tryLogin(loginDto);

        if(userResponseDto!=null) {
            responseResult.setResponseDto(userResponseDto);
            responseResult.setFlag(true);
            System.out.println("Success: Login");
            return ResponseEntity.status(HttpStatus.OK).body(responseResult);
        }
        else {
            ErrorResponseDto errorDto = new ErrorResponseDto(FeedbackMessages.NO_MATCHED_LOGIN);
            responseResult.setResponseDto(errorDto);

            responseResult.setFlag(false);
            System.out.println("Error: No Id matched");
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
            result.setFlag(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

        SignUpCode code = service.trySignUp(signUpDto);

        switch (code) {
            case SUCCESS -> {
                signUpResponseDto.setDescription("회원 가입 성공");
                result.setFlag(true);
                return ResponseEntity.status(HttpStatus.CREATED).body(result);
            }
            case DUPLICATED_ID -> {
                signUpResponseDto.setDescription("ID 중복");
                result.setFlag(false);
                return ResponseEntity.status(HttpStatus.IM_USED).body(result);
            }
            default -> {
                signUpResponseDto.setDescription("회원 가입 실패");
                result.setFlag(false);
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(result);
            }
        }
    }



}

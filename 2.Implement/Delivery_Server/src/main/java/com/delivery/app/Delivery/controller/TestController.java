package com.delivery.app.Delivery.controller;

import com.delivery.app.Delivery.data.dto.TestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {
    @PostMapping("/get-dto")
    public ResponseEntity<TestDto> testFunction(@RequestBody TestDto testDto) {

        System.out.println("Test is Called...");
        System.out.print(testDto.getName() + ", ");
        System.out.println(testDto.getLoginDtoList().get(0).getLoginId() + ", " + testDto.getLoginDtoList().get(1).getLoginId());
        ResponseEntity<TestDto> entity = new ResponseEntity<>(HttpStatus.OK);

        return ResponseEntity.status(HttpStatus.OK).body(testDto);
    }

    @GetMapping("/print-string")
    public String test2Function() {
        System.out.println("Test2 is Called...");
        return "Hello World!";
    }

    @PostMapping("/post")
    public String test3Function(@RequestBody String str) {
        System.out.println("print: " + str);
        return "!";
    }
}

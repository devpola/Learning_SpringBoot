package com.example.mvc.controller;

import com.example.mvc.dto.PostRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostApiController {

    @PostMapping("/post")
    public void post(@RequestBody Map<String, Object> requestData){

        requestData.forEach((key, value) -> {
            System.out.println("key: " + key);
            System.out.println("value: " + value);
        });
    }

    //Dto class의 멤버변수들의 이름과 requestData의 key값을 비교하여 매핑
    @PostMapping("/post2")
    public void post2(@RequestBody PostRequestDto postRequestDto){
        System.out.println(postRequestDto);
    }
}

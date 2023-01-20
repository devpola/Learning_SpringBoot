package com.example.api.controller;

import com.example.api.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

    @RequestMapping("/main")
    public String main(){
        return "main.html";
    }

    // RestController가 아닌 Controller는 일반적으로 Resource 폴더에 있는 자원을 response
    // ResponseBody annotation을 붙이면 json response 가능
    // 하지만 아래의 기능은 RestController에 추가하는 것이 올바름
    @ResponseBody
    @GetMapping("/user")
    public User user(){
        var user = new User();  //java 11부터 타입추론 가능
        user.setName("steve");
        user.setAddress("hello");
        return user;
    }
}

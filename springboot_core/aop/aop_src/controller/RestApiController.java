package com.example.aop.controller;

import com.example.aop.annotation.Decode;
import com.example.aop.annotation.Timer;
import com.example.aop.dto.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RestApiController {

    @GetMapping("/get/{id}")
    public String get(@PathVariable Long id, @RequestParam String name){
        //AOP를 활용하여 log를 통한 디버깅이 가능하기 때문에 실제 코드에서는 아래와 같은 값들을 출력할 필요 없음.
//        System.out.println("get method");
//        System.out.println("get method : " + id);
//        System.out.println("get method : " + name);

        return id + " " + name;
    }

    @PostMapping("/post")
    public User post(@RequestBody User user){
//        System.out.println("post method : " + user);
        return user;
    }

    @Timer
    @DeleteMapping("/delete")
    public void delete() throws InterruptedException {

        //db logic

        Thread.sleep(1000 * 2);
    }

    @Decode
    @PutMapping("/put")
    public User put(@RequestBody User user){
        System.out.println("put");
        System.out.println(user);
        return user;
    }
}

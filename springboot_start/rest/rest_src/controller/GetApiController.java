package com.example.mvc.controller;

import com.example.mvc.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class GetApiController {

    @GetMapping("/hello")   //http://localhost:9090/api/get/hello
    public String hello(){
        return "get hello";
    }

    @RequestMapping(path = "/hi", method = RequestMethod.GET)   //http://localhost:9090/api/get/hi
    public String hi(){
        return "hi";
    }

    // http://localhost:9090/api/get/path-variable/{name}
    @GetMapping("/path-variable/{name}")
    public String pathVariable(@PathVariable String name){
        System.out.println("PathVariable : " + name);
        return "name";
    }
    //매개변수와 GetMapping에 사용하는 path variable 이름 동일해야함
    //But, public String pathVariable(@PathVariable(name = "name") String pathName){ ... 도 가능


    //http://localhost:9090/api/get/query-param?user=steve&email=steve@gmail.com&age=30
    @GetMapping(path="query-param")
    public String queryParam(@RequestParam Map<String, String> queryParam){
        StringBuilder sb = new StringBuilder();

        queryParam.entrySet().forEach( entry -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("\n");

            sb.append(entry.getKey()+" = "+entry.getValue()+"\n");
        });

        return sb.toString();
    }

    // 위 처럼 Map 으로 받는다면, Key를 알 수 없음.
    // 반면에, 아래와 같이 query parameter를 명시적으로도 받을 수 있음
    @GetMapping("query-param02")
    public String queryParam02(@RequestParam String name, @RequestParam String email, @RequestParam int age){
        return name+" "+email+" "+age;
    }

    // 가장 많이 활용하는 방법
    @GetMapping("query-param03")
    public String queryParam03(UserRequest userRequest){

        System.out.println(userRequest.getAge());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getName());

        return userRequest.toString();
    }

}

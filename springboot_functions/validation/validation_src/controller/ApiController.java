package com.example.validation.controller;

import com.example.validation.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ApiController {

    //Validation을 사용하지 않는 경우
    // 아래와 같이 RequestBody로 입력된 객체의 속성들을 일일이 조건문으로 확인해야함.
    @PostMapping("/user_old")
    public ResponseEntity user_old(@RequestBody User user){
        System.out.println(user);

        if(user.getAge() >= 90){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user);
        }
        return ResponseEntity.ok(user);
    }

    //매개변수로 입력받는 객체에 Valid annotation을 붙여주면,
    // 해당 객체의 멤버 변수들의 validation 관련 annotaion을 검사
    @PostMapping("/user")
    public ResponseEntity user(@Valid @RequestBody User user, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            bindingResult.getAllErrors().forEach(objectError -> {
                FieldError field = (FieldError)objectError;
                String message = objectError.getDefaultMessage();

                System.out.println("field : " + field.getField());
                System.out.println(message);

                sb.append("Field : " + field.getField());
                sb.append("Message : " + message);
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }

        //위 검증 단계 통과 후 logic 실행
        //logic

        return ResponseEntity.ok(user);
    }
}

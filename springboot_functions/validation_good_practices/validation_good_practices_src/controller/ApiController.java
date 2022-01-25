package com.example.validation_good_practices.controller;

import com.example.validation_good_practices.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/api/user")
@Validated
public class ApiController {

    //Validated annotation과 함께, parameter들 앞에 validation관련 annotation을 명시해줌으로 검증 가능
    //위 annotation으로 controller에서 parameter에 대한 검증이 되면, dto package 내의 class에 명시한 validation annotation들은 무시
    @GetMapping("")
    public User get(
            @Size(min=5)
            @RequestParam String name,

            @NotNull
            @Min(1)
            @RequestParam Integer age){
        User user = new User();
        user.setName(name);
        user.setAge(age);

        //make NullPointerException
        int a = 10 + age;

        return user;
    }

    @PostMapping("")
    public User post(@Valid @RequestBody User user){
        System.out.println(user);
        return user;
    }

    //Controller advice에서 global로 예외처리를 하는데,
    //특정 controller에서만 예외처리를 하고 싶다면 아래와 같이 해당 controller 내부에서 exceptionhandler 사용하면 된다.
//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e){
//        System.out.println("api controller");
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//    }

    //우선순위는 controller 내의 ExceptionHandler가 더 높기 때문에, controller 내부에서 예외 처리가 된다면
    //controller advice의 예외처리는 실행되지 않는다.
//    @ExceptionHandler(value = NullPointerException.class)
//    public ResponseEntity exception(Exception e){
//        System.out.println("api controller");
//
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
//    }
}

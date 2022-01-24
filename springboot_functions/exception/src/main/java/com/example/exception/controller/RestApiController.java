package com.example.exception.controller;

import com.example.exception.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class RestApiController {

    //required = false -> 필수 x. But, 만약 없다면 null 값이 됨.
    //default 값은 true. -> 해당 값이 request parameter로 넘어오지 않는다면 error 발생
    @GetMapping("")
    public User get(@RequestParam(required = false) String name, @RequestParam(required = false) Integer age){
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
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e){
        System.out.println("api controller");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    //우선순위는 controller 내의 ExceptionHandler가 더 높기 때문에, controller 내부에서 예외 처리가 된다면
    //controller advice의 예외처리는 실행되지 않는다.
    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity exception(Exception e){
        System.out.println("api controller");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

}

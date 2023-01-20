package com.example.ioc;

import org.springframework.stereotype.Component;

import java.util.Base64;

//Component annotation 을 통해 Bean으로 등록
@Component
public class Base64Encoder implements IEncoder{

    @Override
    public String encode(String message) {
        return Base64.getEncoder().encodeToString(message.getBytes());
    }
}

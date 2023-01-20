package com.example.naver_api.controller;

import com.example.naver_api.service.RestTemplateService;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;

@RestController
@RequestMapping("/api")
public class ApiController {

    private RestTemplateService restTemplateService;

    public ApiController(RestTemplateService restTemplateService){
        this.restTemplateService = restTemplateService;
    }

    @GetMapping("/naver")
    public String naver(){
        return restTemplateService.naverApi();
    }
}

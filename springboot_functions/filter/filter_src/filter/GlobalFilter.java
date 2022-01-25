package com.example.filter.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

// global로 filter를 적용시키기 위해서는 @Component 사용
// 특정 urlPattern에 filter를 적용시키기 위해서는
// main 메서드가 있는 class 위에 @ServletComponentScan / filter class위에 @WebFilter 사용
@Slf4j
@WebFilter(urlPatterns = "/api/user/*")
public class GlobalFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // 전처리
        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper((HttpServletResponse) response);
        // * HttpServletRequest, Response로 선언하고 getReader를 통해 request body의 모든 line을 읽어버리면
        // 다시는 읽을 수 없는 상태가 된다.
        // Error : getReader() has already been called for this request
        // But, 위처럼 ContentCachingRequestWrapper로 선언하면, body를 읽으면서 caching을 하기 때문에 해당 에러가 발생하지 않는다.
        // 하지만 생성할 때는 caching을 하지 않고 크기만 할당하기 때문에 doFilter 이후로(framework의 매핑이 끝난 후에) 읽을 수 있다.

        chain.doFilter(httpServletRequest, httpServletResponse);

        String url = httpServletRequest.getRequestURI();

        // 후처리
        String reqContent = new String(httpServletRequest.getContentAsByteArray());
        log.info("request url : {}, request body : {}", url, reqContent);

        String resContent = new String(httpServletResponse.getContentAsByteArray());
        int httpStatus = httpServletResponse.getStatus();

        httpServletResponse.copyBodyToResponse();   //getContentAsByteArray를 통해 body의 내용을 다 읽어들여서, 다시 채워주는 메서드
        log.info("response status : {}, response body : {}", httpStatus, resContent);
    }
}
package com.chen.mock.controller;

import com.chen.mock.config.MockData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author chen
 * @date 2020-11-10-0:27
 */
@RestController
@RequestMapping("/")
@Slf4j
public class MockRequest {

    @Autowired
    private MockData mockData;
    @RequestMapping("/**")
    public ResponseEntity<?> request(HttpServletRequest request, HttpServletResponse response) {
        ResponseEntity<?>  resp;
        try {
            ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
            stringThreadLocal.remove();
            String requestURI = request.getRequestURI();
            String data = mockData.get(requestURI);
            if (Objects.nonNull(data)) {
                if (requestURI.equals("/api/v0/token/authorize")) {
                    resp = ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body(data);
                } else {
                    resp = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(data);
                }
            } else {
                resp = ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp = ResponseEntity.status(500).body("Internal server error");
        }
        return resp;
    }
}
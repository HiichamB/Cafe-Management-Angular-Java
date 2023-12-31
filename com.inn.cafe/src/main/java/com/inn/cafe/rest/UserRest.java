package com.inn.cafe.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RestController
@RequestMapping(path="/user")
public interface UserRest {
    @PostMapping(path = "/signup")
    public ResponseEntity<String> singUp(@RequestBody(required = true)Map<String,String> requestMap);

}

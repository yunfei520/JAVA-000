package com.example.homework07.controller;

import com.example.homework07.model.User;
import com.example.homework07.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Log4j2
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/user/get")
    public User getUser(HttpServletRequest request){
        log.info("Request param is {}", request);
        return userService.queryUser(1);
    }
}

package com.example.test.controller;

import com.example.test.dto.SignInRequest;
import com.example.test.service.impl.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    @GetMapping("/profile")
    public String profile(){
        return "Hello User";
    }
}

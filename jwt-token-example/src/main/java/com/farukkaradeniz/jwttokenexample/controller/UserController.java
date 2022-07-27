package com.farukkaradeniz.jwttokenexample.controller;

import com.farukkaradeniz.jwttokenexample.dto.User;
import com.farukkaradeniz.jwttokenexample.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {


    @GetMapping
    public ResponseEntity<User> getUserDetail() {

        var auth = SecurityContextHolder.getContext().getAuthentication();

        var roles = JwtUtil.getRoles();

        System.out.println(auth.getName());
        return ResponseEntity.ok()
                .build();
    }

}

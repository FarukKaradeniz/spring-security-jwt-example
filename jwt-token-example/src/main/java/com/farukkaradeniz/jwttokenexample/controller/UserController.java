package com.farukkaradeniz.jwttokenexample.controller;

import com.farukkaradeniz.jwttokenexample.dto.User;
import com.farukkaradeniz.jwttokenexample.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<User> getUserDetail0() {

        var auth = SecurityContextHolder.getContext().getAuthentication();

        var roles = JwtUtil.getRoles();

        System.out.println(auth.getName());
        return ResponseEntity.ok()
                .build();
    }


    @GetMapping("/godmin")
    @PreAuthorize("hasAuthority('GOD') or hasAuthority('ADMIN')")
    public ResponseEntity<User> getUserDetail1() {

        var auth = SecurityContextHolder.getContext().getAuthentication();

        var roles = JwtUtil.getRoles();

        System.out.println(auth.getName());
        return ResponseEntity.ok()
                .build();
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<User> getUserDetail2() {

        var auth = SecurityContextHolder.getContext().getAuthentication();

        var roles = JwtUtil.getRoles();

        System.out.println(auth.getName());
        return ResponseEntity.ok()
                .build();
    }

    @GetMapping("/god")
    @PreAuthorize("hasAuthority('GOD')")
    public ResponseEntity<User> getUserDetail3() {

        var auth = SecurityContextHolder.getContext().getAuthentication();

        var roles = JwtUtil.getRoles();

        System.out.println(auth.getName());
        return ResponseEntity.ok()
                .build();
    }

}

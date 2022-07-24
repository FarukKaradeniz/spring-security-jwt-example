package com.farukkaradeniz.jwttokenexample.controller;

import com.farukkaradeniz.jwttokenexample.config.UserService;
import com.farukkaradeniz.jwttokenexample.dto.LoginModel;
import com.farukkaradeniz.jwttokenexample.dto.TokenModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenModel> login(@RequestBody LoginModel loginModel) {
        var user = userService.getUser(loginModel.username());
        System.out.println(user);

        return ResponseEntity.ok(new TokenModel(user.toString()));
    }

}

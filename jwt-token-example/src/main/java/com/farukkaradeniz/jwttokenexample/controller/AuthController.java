package com.farukkaradeniz.jwttokenexample.controller;

import com.farukkaradeniz.jwttokenexample.dto.LoginModel;
import com.farukkaradeniz.jwttokenexample.dto.TokenModel;
import com.farukkaradeniz.jwttokenexample.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenModel> login(@RequestBody LoginModel loginModel) {
        var token = authService.createToken(loginModel.username());

        return ResponseEntity.ok(new TokenModel(token));
    }

}

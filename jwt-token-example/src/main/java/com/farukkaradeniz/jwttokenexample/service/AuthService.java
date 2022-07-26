package com.farukkaradeniz.jwttokenexample.service;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AuthService {
    private final JwtEncoder jwtEncoder;
    private final UserService userService;

    public AuthService(JwtEncoder jwtEncoder, UserService userService) {
        this.jwtEncoder = jwtEncoder;
        this.userService = userService;
    }

    public String createToken(String username) {
        Instant now = Instant.now();
        long expiry = 36000L;

        var user = userService.getUser(username);

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(username)
                .claim("roles", "USER,ADMIN,GOD")
                .claim("omer", "test")
                .claim("address", user.getAddress())
                .build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}

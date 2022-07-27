package com.farukkaradeniz.jwttokenexample.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.Instant;

@Service
public class AuthService {
    private final JwtEncoder jwtEncoder;
    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    public AuthService(JwtEncoder jwtEncoder, UserService userService, PasswordEncoder passwordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public String createToken(String username, String password) {
        Instant now = Instant.now();
        long expiry = 36000L;

        var user = userService.getUser(username);

        Assert.notNull(password, "Password should not be null");
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return "";
        }

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

package com.farukkaradeniz.jwttokenexample.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public class JwtUtil {

    public static Jwt getToken() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof Jwt) {
            return ((Jwt) authentication.getPrincipal());
        }
        return null;
    }

    public static Map<String, Object> getClaims() {
        return getToken() == null ? Map.of() : getToken().getClaims();
    }

    public static Map<String, Object> getHeaders() {
        return getToken() == null ? Map.of() : getToken().getHeaders();

    }

    public static List<String> getRoles() {
        return (List<String>) getClaims().getOrDefault("scope", List.of());
    }

    public static Instant getExpireDate() {
        return getToken() == null ? Instant.now() : getToken().getExpiresAt();
    }

    public static String getTokenValue() {
        return getToken() == null ? null : getToken().getTokenValue();
    }

}

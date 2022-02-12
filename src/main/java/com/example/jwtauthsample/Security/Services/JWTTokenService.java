package com.example.jwtauthsample.Security.Services;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class JWTTokenService {
    private static HashMap<String, String> tokens = new HashMap<>();

    public void register(String principal, String token) {
        tokens.put(principal, token);
    }

    public boolean isCorrectToken(String principal, String token) {
        String foundToken = tokens.get(principal);
        return foundToken != null && foundToken.equals(token);
    }
}

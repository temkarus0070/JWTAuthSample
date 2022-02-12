package com.example.jwtauthsample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/secret")
    public String getSecrets() {
        return "secret";
    }
}

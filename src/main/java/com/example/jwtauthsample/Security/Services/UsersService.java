package com.example.jwtauthsample.Security.Services;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.ArrayList;
import java.util.List;


public class UsersService {
    private static List<UsernamePasswordAuthenticationToken> list = new ArrayList<>();

    public void register(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        list.add(usernamePasswordAuthenticationToken);
    }

    public UsernamePasswordAuthenticationToken get(String login) {
        return list.stream().filter(user -> user.getPrincipal().toString().equals(login)).findFirst().get();
    }
}

package com.example.jwtauthsample.Security.Filters;

import com.example.jwtauthsample.Security.JWTToken;
import com.example.jwtauthsample.Security.Services.JWTTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JWTAuthProvider implements AuthenticationProvider {
    @Autowired
    private JWTTokenService jwtTokenService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (jwtTokenService.isCorrectToken(authentication.getPrincipal().toString(), authentication.getCredentials().toString())) {
            return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), null, List.of(() -> "USER"));
        } else throw new BadCredentialsException("your jwt token is invalid");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JWTToken.class.isAssignableFrom(authentication);
    }
}

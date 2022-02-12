package com.example.jwtauthsample.Security.Filters;

import com.example.jwtauthsample.Security.Services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoginAuthProvider implements AuthenticationProvider {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersService usersService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = usersService.get(authentication.getPrincipal().toString());
        if (passwordEncoder.matches(authentication.getCredentials().toString(), usernamePasswordAuthenticationToken.getCredentials().toString())) {
            return new UsernamePasswordAuthenticationToken(usernamePasswordAuthenticationToken.getPrincipal(), usernamePasswordAuthenticationToken.getCredentials(), List.of(() -> "USER"));
        } else throw new BadCredentialsException("YOUR DATA IS INVALID");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}

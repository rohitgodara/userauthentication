package com.poc.userauthentication.controller;

import com.poc.userauthentication.model.AuthRequest;
import com.poc.userauthentication.service.IUserService;
import com.poc.userauthentication.service.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import static com.poc.userauthentication.util.AppConstants.NOT_FOUND;

@RestController
@AllArgsConstructor
@RequestMapping("auth")
public class AuthController {

    private final IUserService userService;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @PostMapping("signin")
    public ResponseEntity<Object> signIn(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated())
            return new ResponseEntity<>(jwtService.generateToken(authRequest.getUsername()), HttpStatus.CREATED);
        else
            throw new UsernameNotFoundException(String.format(
                    NOT_FOUND, authRequest.getUsername()));
    }
}

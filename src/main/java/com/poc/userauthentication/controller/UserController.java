package com.poc.userauthentication.controller;

import com.poc.userauthentication.model.UserModel;
import com.poc.userauthentication.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("users")
public class UserController {

    private final IUserService userService;

    @PostMapping("signup")
    public ResponseEntity<Object> create(@RequestBody UserModel userData) {
        return ResponseEntity.created(URI.create(userService.create(userData).getId().toString())).build();
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }
}

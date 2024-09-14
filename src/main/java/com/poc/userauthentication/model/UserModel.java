package com.poc.userauthentication.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    String firstName;
    String lastName;
    String email;
    String username;
    String password;
    String role;
    int age;
}

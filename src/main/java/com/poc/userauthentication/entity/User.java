package com.poc.userauthentication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    @Column(unique=true)
    private String username;
    private String password;
    private String role;
    private String createdOn;
    private String modifiedOn;
    private Boolean isActive;
    private int age;
}

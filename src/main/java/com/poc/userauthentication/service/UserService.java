package com.poc.userauthentication.service;

import com.poc.userauthentication.entity.User;
import com.poc.userauthentication.model.UserModel;
import com.poc.userauthentication.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    @Override
    public User create(UserModel userData) {
        User user = User
                .builder()
                .firstName(userData.getFirstName())
                .lastName(userData.getLastName())
                .username(userData.getUsername())
                .email(userData.getEmail())
                .password(userData.getPassword())
                .role(userData.getRole())
                .age(userData.getAge())
                .isActive(Boolean.TRUE)
                .createdOn(Instant.now().toString())
                .modifiedOn(Instant.now().toString())
                .build();
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}

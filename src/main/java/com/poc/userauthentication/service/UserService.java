package com.poc.userauthentication.service;

import com.poc.userauthentication.entity.User;
import com.poc.userauthentication.entity.UserInfoDetails;
import com.poc.userauthentication.model.UserModel;
import com.poc.userauthentication.repository.IUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static com.poc.userauthentication.util.AppConstants.NOT_FOUND;

@Slf4j
@Service
@AllArgsConstructor
public class UserService implements IUserService, UserDetailsService {

    private final IUserRepository userRepository;

    private final PasswordEncoder encoder;

    @Override
    public User create(UserModel userData) {
        User user = User
                .builder()
                .firstName(userData.getFirstName())
                .lastName(userData.getLastName())
                .username(userData.getUsername())
                .email(userData.getEmail())
                .password(encoder.encode(userData.getPassword()))
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


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername :: {}", username);
        Optional<User> userDetail = userRepository.findByUsername(username);
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(
                        NOT_FOUND, username)));
    }

}

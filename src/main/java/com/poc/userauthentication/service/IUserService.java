package com.poc.userauthentication.service;

import com.poc.userauthentication.entity.User;
import com.poc.userauthentication.model.UserModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface IUserService {

    User create(UserModel userData);

    List<User> findAll();

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}

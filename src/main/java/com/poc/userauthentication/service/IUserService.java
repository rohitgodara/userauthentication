package com.poc.userauthentication.service;

import com.poc.userauthentication.entity.User;
import com.poc.userauthentication.model.UserModel;

import java.util.List;

public interface IUserService {

    User create(UserModel userData);

    List<User> findAll();
}

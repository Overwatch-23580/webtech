package com.project.lifebank.service;

import com.project.lifebank.dto.UserDto;
import com.project.lifebank.model.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
    List<UserDto> findAllUsers();
}

package com.example.UserRepository.logic.service;

import com.example.UserRepository.logic.dto.UserDto;

public class UserServiceImpl extends UserService<UserDto> {

    public void createUser(UserDto user){
        createInternal(user);
    }

    public UserDto updateUser(String email, UserDto user){
        return updateInternal(email, user);
    }

    public UserDto findUser(String email){
        return findInternal(email);
    }
}

package com.example.UserRepository.logic.service;

import com.example.UserRepository.logic.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl extends UserService<UserDto> {

    public UserDto createUser(UserDto user){
        return createInternal(user);
    }

    public UserDto updateUser(String email, UserDto user){
        return updateInternal(email, user);
    }

    public UserDto findUser(String email){
        return findInternal(email);
    }
}

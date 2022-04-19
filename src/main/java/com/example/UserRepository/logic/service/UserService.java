package com.example.UserRepository.logic.service;

import com.example.UserRepository.db.model.User;
import com.example.UserRepository.db.repository.UserRepository;
import com.example.UserRepository.logic.dto.UserDto;
import com.example.UserRepository.logic.exceptions.MyUserException;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class UserService<T extends UserDto> {

    @Autowired
    UserRepository userRepository;

    protected final void createInternal(T resource){
        if (userRepository.existsByEmail(resource.getEmail())){
            throw new MyUserException("User is already created");
        }
        userRepository.save(createUser(resource));
    }

    protected final UserDto updateInternal(String email, T resource){
        if (!userRepository.existsByEmail(email)){
            throw new MyUserException("We could not find a user with the given email");
        } else if (!email.equalsIgnoreCase(resource.getEmail())) {
            throw new MyUserException("email in the URI doesn't match the user email");
        }
        return createUserDto(userRepository.save(createUser(resource)));
    }

    protected final UserDto findInternal(String email) {
        if (!userRepository.existsByEmail(email)){
            throw new MyUserException("We could not find a user with the given email");
        }
        return createUserDto(userRepository.findByEmail(email).get());
    }

    private User createUser(T userDto){
        long id = 0L;
        if (userRepository.existsByEmail(userDto.getEmail())){
            id = userRepository.findByEmail(userDto.getEmail()).get().getId();
        }
        return createUser(id, userDto);
    }

    private User createUser(long id, T userDto){
        User user = new User(userDto.getEmail(), userDto.getFirstName(), userDto.getLastName(), userDto.getPhoneNumber());
        if (id != 0)
            user.setId(id);
        return user;
    }

    private UserDto createUserDto(User user) {
        return new UserDto(user.getEmail(), user.getFirstName(), user.getLastName(), user.getPhoneNumber());
    }
}

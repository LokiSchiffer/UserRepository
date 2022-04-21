package com.example.UserRepository.web.controller;

import com.example.UserRepository.logic.dto.UserDto;
import com.example.UserRepository.logic.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/users")
public class WebController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public UserDto create(@RequestBody @Valid UserDto user) {
        return userService.createUser(user);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public UserDto update(@PathVariable("id") final String email, @RequestBody @Valid final UserDto user){
        return userService.updateUser(email, user);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserDto find(@PathVariable("id") final String email){
        return userService.findUser(email);
    }
}

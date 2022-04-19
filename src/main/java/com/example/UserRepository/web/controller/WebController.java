package com.example.UserRepository.web.controller;

import com.example.UserRepository.logic.dto.UserDto;
import com.example.UserRepository.logic.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/users")
public class WebController extends UserService<UserDto> {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid UserDto user) {
        createInternal(user);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public UserDto update(@PathVariable("id") final String email, @RequestBody @Valid final UserDto user){
        return updateInternal(email, user);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserDto find(@PathVariable("id") final String email){
        return findInternal(email);
    }
}

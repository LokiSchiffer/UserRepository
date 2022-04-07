package com.example.UserRepository.Web;

import com.example.UserRepository.Model.User;
import com.example.UserRepository.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class WebController {

    @Autowired
    UserRepository userRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody User user) {
        if (userRepository.existsById(user.getEmail())){
            throw new IllegalArgumentException("User is already created");
        }
        userRepository.save(user);
    }
}

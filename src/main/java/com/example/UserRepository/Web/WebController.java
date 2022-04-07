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

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public User update(@PathVariable("id") final String email, @RequestBody final User user){
        if (!userRepository.existsById(email)){
            throw new IllegalArgumentException("User with that email doesn't exist");
        } else if (!email.equalsIgnoreCase(user.getEmail())) {
            throw new IllegalArgumentException("email in the URI doesn't match the user email");
        }
        return userRepository.save(user);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public User getUser(@PathVariable("id") final String email){
        if (!userRepository.existsById(email)){
            throw new IllegalArgumentException("User with that email doesn't exist");
        }
        return userRepository.findById(email).get();
    }
}

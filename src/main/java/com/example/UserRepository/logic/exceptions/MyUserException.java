package com.example.UserRepository.logic.exceptions;

public class MyUserException extends RuntimeException{

    public MyUserException() {
        super();
    }

    public MyUserException(final String message) {
        super(message);
    }
}

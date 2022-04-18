package com.example.UserRepository.Exceptions;

public class MyUserException extends RuntimeException{

    public MyUserException() {
        super();
    }

    public MyUserException(final String message) {
        super(message);
    }
}

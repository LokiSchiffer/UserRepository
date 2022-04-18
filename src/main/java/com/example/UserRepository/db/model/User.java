package com.example.UserRepository.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class User {

    @Id
    @Email(message = "Email address not valid")
    @NotEmpty(message = "Email is required")
    private String email;

    @Column(nullable = false)
    @Size(max = 50, message = "Firstname out of size")
    @NotNull(message = "Parameter should not be null")
    @NotEmpty(message = "First name is required")
    private String firstName;

    @Column(nullable = false)
    @Size(max = 50, message = "Lastname out of size")
    @NotNull(message = "Parameter should not be null")
    @NotEmpty(message = "Last name is required")
    private String lastName;

    @Column(nullable = false)
    @Pattern(regexp = "^\\+503\\d{8}", message = "Invalid number, it has to be in the form of" +
            " \"+503 ##### ####\"")
    private String phoneNumber;

    protected User() {

    }

    public User(String email, String firstName, String lastName, String phoneNumber) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public User(User user) {
        this(user.getEmail(), user.getFirstName(), user.getLastName(), user.getPhoneNumber());
    }

    public String getEmail() {
        return email;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}

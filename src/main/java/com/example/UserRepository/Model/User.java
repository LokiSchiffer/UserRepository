package com.example.UserRepository.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User {

    @Id
    @Email
    private String email;

    @Column(nullable = false)
    @Size(max = 50, message = "Firstname out of size")
    @NotNull
    private String firstName;

    @Column(nullable = false)
    @Size(max = 50)
    @NotNull
    private String lastName;

    @Column(nullable = false)
    private long phoneNumber;

    protected User() {

    }

    public User(String email, String firstName, String lastName, long phoneNumber) {
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

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
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

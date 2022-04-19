package com.example.UserRepository.logic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @Email(message = "Email address not valid")
    @NotEmpty(message = "Email is required")
    private String email;

    @Size(max = 50, message = "Firstname out of size")
    @NotNull(message = "Parameter should not be null")
    @NotEmpty(message = "First name is required")
    private String firstName;

    @Size(max = 50, message = "Lastname out of size")
    @NotNull(message = "Parameter should not be null")
    @NotEmpty(message = "Last name is required")
    private String lastName;

    @Pattern(regexp = "^\\+503\\d{8}", message = "Invalid number, it has to be in the form of" +
            " \"+503 ##### ####\"")
    private String phoneNumber;
}

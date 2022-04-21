package com.example.UserRepository.web.controller;

import com.example.UserRepository.logic.dto.UserDto;
import com.example.UserRepository.logic.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(WebController.class)
class WebControllerTest {

    @Autowired
    private WebController webController;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserServiceImpl userService;

    @Test
    public void findUserByEmailTest() throws Exception {

        UserDto user = new UserDto();

        user.setEmail("loki@mail.com");
        user.setFirstName("Norberto");
        user.setLastName("Mosquera");
        user.setPhoneNumber("+50312345678");

        when(userService.findUser(anyString())).thenReturn(user);

        mvc.perform(MockMvcRequestBuilders.get("/users/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("loki@mail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Norberto"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Mosquera"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("+50312345678"))
                .andExpect(status().isOk());
    }

    @Test
    public void createUserTest() throws Exception {

        UserDto user = new UserDto();

        user.setEmail("loki@mail.com");
        user.setFirstName("Norberto");
        user.setLastName("Mosquera");
        user.setPhoneNumber("+50312345678");

        doReturn(user).when(userService).createUser(any(UserDto.class));

        mvc.perform(MockMvcRequestBuilders.post("/users")
                .content(new ObjectMapper().writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("loki@mail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Norberto"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Mosquera"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("+50312345678"))
                .andExpect(status().isCreated());

    }

}
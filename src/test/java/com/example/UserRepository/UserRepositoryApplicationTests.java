package com.example.UserRepository;

import com.example.UserRepository.logic.dto.UserDto;
import com.example.UserRepository.web.controller.WebController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class  UserRepositoryApplicationTests {

	@Autowired
	private WebController webController;

	@Test
	void createUserTest() {
		UserDto newUser = new UserDto("prueba1@mail.com", randomAlphabetic(6),
				randomAlphabetic(16), "+50312345678");
		webController.create(newUser);
		assertNotNull(webController.find(newUser.getEmail()));
	}

	@Test
	void updateUserTest() {
		UserDto newUser = new UserDto("prueba2@mail.com", randomAlphabetic(6),
				randomAlphabetic(16), "+50312345678");
		webController.create(newUser);
		UserDto secondUser = new UserDto();
		secondUser.setEmail(newUser.getEmail());
		secondUser.setFirstName("Loki");
		secondUser.setLastName("Schiffer");
		secondUser.setPhoneNumber(newUser.getPhoneNumber());
		UserDto retrievedUser = webController.update(secondUser.getEmail(), secondUser);
		assertThat(retrievedUser, is(equalTo(secondUser)));
	}

}

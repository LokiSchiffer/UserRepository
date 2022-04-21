package com.example.UserRepository;

import com.example.UserRepository.logic.dto.UserDto;
import com.example.UserRepository.logic.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
@Rollback
public class  UserRepositoryApplicationTests {

	@Autowired
	private UserServiceImpl userService;

	@Test
	void createUserTest() {
		UserDto newUser = new UserDto("prueba1@mail.com", randomAlphabetic(6),
				randomAlphabetic(16), "+50312345678");
		UserDto userCreated = userService.createUser(newUser);
		assertNotNull(userCreated);
	}

	@Test
	void updateUserTest() {
		UserDto newUser = new UserDto("prueba1@mail.com", randomAlphabetic(6),
				randomAlphabetic(16), "+50312345678");
		userService.createUser(newUser);
		UserDto secondUser = new UserDto();
		secondUser.setEmail(newUser.getEmail());
		secondUser.setFirstName("Loki");
		secondUser.setLastName("Schiffer");
		secondUser.setPhoneNumber(newUser.getPhoneNumber());
		UserDto retrievedUser = userService.updateUser(secondUser.getEmail(), secondUser);
		assertThat(retrievedUser, is(equalTo(secondUser)));
	}

	@Test
	void findUserTest(){
		UserDto newUser = new UserDto("prueba1@mail.com", randomAlphabetic(6),
				randomAlphabetic(16), "+50312345678");
		userService.createUser(newUser);
		UserDto userCreated = userService.findUser(newUser.getEmail());
		assertThat(userCreated, is(equalTo(newUser)));
	}
}

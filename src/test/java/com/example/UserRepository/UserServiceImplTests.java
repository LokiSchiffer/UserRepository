package com.example.UserRepository;

import com.example.UserRepository.logic.dto.UserDto;
import com.example.UserRepository.logic.exceptions.MyUserException;
import com.example.UserRepository.logic.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserServiceImplTests {

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
		UserDto newUser = new UserDto("prueba2@mail.com", randomAlphabetic(6),
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
		UserDto newUser = new UserDto("prueba3@mail.com", randomAlphabetic(6),
				randomAlphabetic(16), "+50312345678");
		userService.createUser(newUser);
		UserDto userCreated = userService.findUser(newUser.getEmail());
		assertThat(userCreated, is(equalTo(newUser)));
	}

	@Test
	void duplicateUserCreationExceptionTest(){
		UserDto newUser = new UserDto("prueba4@mail.com", randomAlphabetic(6),
				randomAlphabetic(16), "+50312345678");
		userService.createUser(newUser);
		MyUserException exception = assertThrows(MyUserException.class, () -> {
			userService.createUser(newUser);
		});
		assertThat(exception.getClass(), is(equalTo(MyUserException.class)));
	}

	@Test
	void updateUserWithWrongMailExceptionTest() {
		UserDto newUser = new UserDto("prueba5@mail.com", randomAlphabetic(6),
				randomAlphabetic(16), "+50312345678");
		userService.createUser(newUser);
		UserDto secondUser = new UserDto();
		secondUser.setEmail(newUser.getEmail());
		secondUser.setFirstName("Loki");
		secondUser.setLastName("Schiffer");
		secondUser.setPhoneNumber(newUser.getPhoneNumber());
		MyUserException exception = assertThrows(MyUserException.class, () -> {
			userService.updateUser("prueba@mail.com", secondUser);
		});
		assertThat(exception.getClass(), is(equalTo(MyUserException.class)));
	}

	@Test
	void updateUserWithDifferentMailsExceptionTest() {
		UserDto newUser = new UserDto("prueba6@mail.com", randomAlphabetic(6),
				randomAlphabetic(16), "+50312345678");
		userService.createUser(newUser);
		newUser = new UserDto("prueba7@mail.com", randomAlphabetic(6),
				randomAlphabetic(16), "+50312345678");
		userService.createUser(newUser);
		UserDto secondUser = new UserDto();
		secondUser.setEmail(newUser.getEmail());
		secondUser.setFirstName("Loki");
		secondUser.setLastName("Schiffer");
		secondUser.setPhoneNumber(newUser.getPhoneNumber());
		MyUserException exception = assertThrows(MyUserException.class, () -> {
			userService.updateUser("prueba6@mail.com", secondUser);
		});
		assertThat(exception.getClass(), is(equalTo(MyUserException.class)));
	}

	@Test
	void findUserExceptionTest() {
		MyUserException exception = assertThrows(MyUserException.class, () -> {
			assertNotNull(userService.findUser("prueba@mail.com"));
		});
		assertThat(exception.getClass(), is(equalTo(MyUserException.class)));
	}

	@Test
	void dataIntegrityViolationExceptionTest(){
		UserDto newUser = new UserDto("prueba8@mail.com", null,
				randomAlphabetic(16), "+50312345678");
		DataIntegrityViolationException exception = assertThrows(DataIntegrityViolationException.class, () -> {
			userService.createUser(newUser);
		});
		assertThat(exception.getClass(), is(equalTo(DataIntegrityViolationException.class)));
	}
}

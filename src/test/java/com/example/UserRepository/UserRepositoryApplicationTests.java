package com.example.UserRepository;

import com.example.UserRepository.Model.User;
import com.example.UserRepository.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class  UserRepositoryApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	void WhenSavingNewProject_thenSuccess() {
		User newUser = new User(randomAlphabetic(16), randomAlphabetic(6),
				randomAlphabetic(16), "123456789");
		assertNotNull(userRepository.save(newUser));
	}

}

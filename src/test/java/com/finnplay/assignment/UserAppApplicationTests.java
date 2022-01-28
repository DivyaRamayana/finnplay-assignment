package com.finnplay.assignment;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import com.finnplay.assignmet.UserAppApplication;
import com.finnplay.assignmet.model.User;
import com.finnplay.assignmet.repository.UserRepository;

@SpringBootTest(classes = UserAppApplication.class)
class UserAppApplicationTests {
	
	@Autowired
	private UserRepository repo;
	
	@Test
	@Order(1)
	public void testCreateUser() {
		User user = new User();
		user.setEmail("iamdivvyyaarr@gmail.com");
		user.setPassword("password");
		user.setConfirmPassword("password");
		user.setFirstName("Divya");
		user.setLastName("Ramayana");
		user.setDateOfBirth(Date.valueOf("1994-01-28"));
		repo.save(user);
	}
	
	@Test
	@Order(2)
	public void testFindByEmail() {
		String email = "divya@gmail.com";
		User user = new User();
		user = repo.findByEmail(email);
		
		assertThat(user.getEmail()).isEqualTo(email);
	}	
	
	/*
	 * @Test void contextLoads() { }
	 */

}

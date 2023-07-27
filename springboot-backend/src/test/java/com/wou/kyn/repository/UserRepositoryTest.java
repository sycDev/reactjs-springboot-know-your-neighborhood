package com.wou.kyn.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;

import com.wou.kyn.entity.Role;
import com.wou.kyn.entity.User;

@DataJpaTest
@Rollback(true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@EnableJpaAuditing
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Test
	public void testCreateUser() {
		// User account details
		String email = "user@example.com";
		String username = "user";
		String rawPassword = "User123#";
		PasswordEncoder passEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passEncoder.encode(rawPassword);

		// Create User account
		User user = new User(username, email, encodedPassword);
		user.setProvider("LOCAL");

		// Save User account to database
		User userToCreate = userRepository.save(user);

		// Retrieve the User account from database
		User userSaved = userRepository.findByEmail(email).get();

		// Verify that the User account is successfully saved
		assertThat(userToCreate.getEmail()).isEqualTo(userSaved.getEmail());
	}

	@Test
	public void testRoleAssignToUser() {
		// Retrieve the User account from database
		User user = userRepository.findByEmail("user@example.com").get();

		// Retrieve USER role from database
		Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository.findByRoleName("USER").get();
		roles.add(userRole);

		// Set USER role for User account
		user.setRoles(roles);

		// Verify that the USER role is successfully assigned
		assertThat(user.getRoles().size()).isEqualTo(1);
	}
}

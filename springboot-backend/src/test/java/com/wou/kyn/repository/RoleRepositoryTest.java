package com.wou.kyn.repository;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

import com.wou.kyn.entity.Role;

@DataJpaTest
@Rollback(true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@RunWith(SpringJUnit4ClassRunner.class)
public class RoleRepositoryTest {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Test
	public void testCreateRoles() {
		// Create USER role
		Role userRole = new Role("USER");
		// Create ADMIN role
		Role adminRole = new Role("ADMIN");
		
		// Save the roles
		roleRepository.save(userRole);
		roleRepository.save(adminRole);
		
		// Retrieve all records from database
		List<Role> roles = roleRepository.findAll();
		
		// Verify total of records match to 2
		assertThat(roles.size()).isEqualTo(2);
	}
}

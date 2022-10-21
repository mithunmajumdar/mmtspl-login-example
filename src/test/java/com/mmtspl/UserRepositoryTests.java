package com.mmtspl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.mmtspl.bean.Users;
import com.mmtspl.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UserRepository repo;
	
	@Test
	public void testCreateUser() {
		Users user = new Users();
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = null;
		
		user.setEmail("root.admin@gmail.com");
		user.setPassword(encodedPassword = passwordEncoder.encode("admin"));
		user.setFirstName("root");
		user.setLastName("admin");
		
		Users savedUser = repo.save(user);
		
		Users existUser = entityManager.find(Users.class, savedUser.getId());
		
		assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
		
	}
	
	@Test
	public void testFindByEmail() {
		String email = "root.admin@gmail.com";
		Users user = repo.findByEmail(email);
		
		assertThat(user.getEmail()).isEqualTo(email);
	}
}

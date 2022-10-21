package com.mmtspl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mmtspl.bean.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
	@Query("SELECT u FROM Users u WHERE u.email = ?1")
	public Users findByEmail(String email);
	
}

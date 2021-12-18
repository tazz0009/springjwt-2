package com.tazz009.jwttest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tazz009.jwttest.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String name);
	
}

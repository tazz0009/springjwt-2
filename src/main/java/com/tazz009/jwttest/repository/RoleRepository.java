package com.tazz009.jwttest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tazz009.jwttest.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByName(String name);
	
}

package com.tazz009.jwttest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tazz009.jwttest.domain.Role;
import com.tazz009.jwttest.domain.User;
import com.tazz009.jwttest.repository.RoleRepository;
import com.tazz009.jwttest.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

	private final UserRepository userRepo;
	private final RoleRepository roleRepo;
	
	@Override
	public User saveUser(User user) {
		log.info("saveUser {}", user);
		return userRepo.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		log.info("saveRole {}", role);
		return roleRepo.save(role);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		log.info("addRoleToUser {} , {}", username, roleName);
		User user = userRepo.findByUsername(username);
		Role role = roleRepo.findByName(roleName);
		user.getRoles().add(role);
		userRepo.save(user);
	}

	@Override
	public User getUser(String username) {
		log.info("getUser {}", username);
		return userRepo.findByUsername(username);
	}

	@Override
	public List<User> getUsers() {
		log.info("getUsers");
		return userRepo.findAll();
	}

}

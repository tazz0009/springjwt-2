package com.tazz009.jwttest.service;

import java.util.List;

import com.tazz009.jwttest.domain.Role;
import com.tazz009.jwttest.domain.User;

public interface UserService {
	User saveUser(User user);
	Role saveRole(Role role);
	void addRoleToUser(String username, String roleName);
	User getUser(String username);
	List<User> getUsers();
}

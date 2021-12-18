package com.tazz009.jwttest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tazz009.jwttest.domain.Role;
import com.tazz009.jwttest.domain.User;
import com.tazz009.jwttest.service.UserService;

@SpringBootApplication
public class Jwttest02Application {

	public static void main(String[] args) {
		SpringApplication.run(Jwttest02Application.class, args);
	}

//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	
	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(Role.builder().name("ROLE_USER").build());
			userService.saveRole(Role.builder().name("ROLE_MANAGER").build());
			userService.saveRole(Role.builder().name("ROLE_ADMIN").build());
			userService.saveRole(Role.builder().name("ROLE_SUPER_ADMIN").build());
			
			userService.saveUser(User.builder().name("강백호").username("tazz001").password("1234").build());
			userService.saveUser(User.builder().name("서태웅").username("tazz002").password("1234").build());
			userService.saveUser(User.builder().name("정대만").username("tazz003").password("1234").build());
			userService.saveUser(User.builder().name("채치수").username("tazz004").password("1234").build());
//			userService.saveUser(User.builder().name("강백호").username("tazz001").password(passwordEncoder().encode("1234")).build());
//			userService.saveUser(User.builder().name("서태웅").username("tazz002").password(passwordEncoder().encode("1234")).build());
//			userService.saveUser(User.builder().name("정대만").username("tazz003").password(passwordEncoder().encode("1234")).build());
//			userService.saveUser(User.builder().name("채치수").username("tazz004").password(passwordEncoder().encode("1234")).build());
			
			userService.addRoleToUser("tazz001", "ROLE_USER");
			userService.addRoleToUser("tazz001", "ROLE_MANAGER");
			userService.addRoleToUser("tazz002", "ROLE_MANAGER");
			userService.addRoleToUser("tazz003", "ROLE_ADMIN");
			userService.addRoleToUser("tazz004", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("tazz004", "ROLE_ADMIN");
			userService.addRoleToUser("tazz004", "ROLE_USER");
		};
	}
}

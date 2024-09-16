package com.loctran.service;

import com.loctran.service.user.Role;
import com.loctran.service.user.User;
import com.loctran.service.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class ServiceApplication implements CommandLineRunner {

	private final UserRepository userRepository;
	private final PasswordEncoder	passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String password = "123456";
		String passwordEncoded = passwordEncoder.encode(password);
			User user = User.builder().name("Quản trị viên ").email("admin@admin.com").password(passwordEncoded).role(
					Role.ADMIN).build();
			userRepository.save(user);
	}
}

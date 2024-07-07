package com.example.Back;

import com.example.Back.security.auth.AuthenticationService;
import com.example.Back.security.auth.RegisterRequest;
import com.example.Back.security.user.User;
import com.example.Back.security.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.example.Back.security.user.Role.ADMIN;

@SpringBootApplication
public class BackApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service,
			UserRepository userRepository
	) {
		return args -> {
			if(!userRepository.existsUserByEmail("admin@mail.com")){

				var admin = RegisterRequest.builder()
						.name("Admin")
						.email("admin@mail.com")
						.password("password")
						.role(ADMIN)
						.address("")
						.phoneNumber("")
						.build();
				System.out.println("Admin token: " + service.register(admin).getAccessToken());
			}

		};
	}

}

package com.tutorial.springsecurityjwt;

import com.tutorial.springsecurityjwt.config.RsaKeyConfigProperties;
import com.tutorial.springsecurityjwt.user.dto.User;
import com.tutorial.springsecurityjwt.user.repository.IUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableConfigurationProperties(RsaKeyConfigProperties.class)
@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement

public class SpringSecurityJwtApplication {
//108209
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtApplication.class, args);
		IUserRepository repository = null;
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		initializeUser(repository,passwordEncoder);
	}

	@Bean
	public static CommandLineRunner initializeUser(IUserRepository IUserRepository, BCryptPasswordEncoder passwordEncoder) {
		return args -> {

				User user = new User();
				user.setUsername("exampleuser");
				user.setEmail("example@gmail.com");
				user.setPassword(passwordEncoder.encode("examplepassword"));

				// Save the user to the database
			System.out.println("userrrrrr"+ user);
				IUserRepository.save(user);

		};
	}

}

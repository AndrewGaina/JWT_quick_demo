package com.exampleJWT.JWTdemo;

import com.exampleJWT.JWTdemo.Model.Role;
import com.exampleJWT.JWTdemo.Model.UserApp;
import com.exampleJWT.JWTdemo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class JwTdemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(JwTdemoApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	};

	UserService userService;

	@Autowired @Lazy
	public void setUserService(UserService userService){
		this.userService = userService;
	}

	@Override
	public void run(String... args) throws Exception {
		Thread.sleep(5000);
		userService.saveRole(new Role(null,"USER"));
		userService.saveRole(new Role(null,"ADMIN"));
		userService.saveUser(new UserApp(null,"Andrew", "AndrewDude", "root", null));
		userService.saveUser(new UserApp(null,"Mikael", "Mikael1234", "user", null));
		userService.addRoleToUser("AndrewDude", "USER");
		userService.addRoleToUser("AndrewDude", "ADMIN");
		userService.addRoleToUser("Mikael1234", "USER");
	}
}

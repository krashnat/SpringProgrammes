package com.bridgelabz.fundooapp.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class Configuration1 {

	@Bean
	public BCryptPasswordEncoder getPasswordEncryption() {
		return new BCryptPasswordEncoder();
	}

}

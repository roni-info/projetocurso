package br.com.roni.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.roni.service.DBService;
import br.com.roni.service.EmailService;
import br.com.roni.service.MockEmailService;
import br.com.roni.service.SmtpMailService;

@Configuration
@Profile("test")
public class TestConfig {
	@Autowired
	private DBService dbService;

	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbService.instantiateTestDatabase();
		return true;
	}
	
/*	@Bean
	public EmailService emailService () {
		return new MockEmailService();
	}
*/	
	@Bean
	public EmailService emailService() {
		return new SmtpMailService();
	}

}

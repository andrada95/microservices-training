package net.safedata.microservices.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FirstServiceApplication {

	public static void main(String[] args) {
		// viewer service
		SpringApplication.run(FirstServiceApplication.class, args);
	}
}

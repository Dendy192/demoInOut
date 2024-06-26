package com.dendy.countinout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CountInOutApplication {
	private static final Logger LOGGER = LogManager.getLogger(CountInOutApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(CountInOutApplication.class, args);

	}

}

package pl.grzegorz2047.ticketentrysystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TicketEntrySystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketEntrySystemApplication.class, args);
	}


}

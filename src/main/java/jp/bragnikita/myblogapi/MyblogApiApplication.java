package jp.bragnikita.myblogapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class MyblogApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyblogApiApplication.class, args);
	}

}

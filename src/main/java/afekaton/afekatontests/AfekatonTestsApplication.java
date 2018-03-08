package afekaton.afekatontests;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.EntityManagerFactory;


@SpringBootApplication
public class AfekatonTestsApplication {

	@Bean
	public WebMvcConfigurer corsConfiguration(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods("POST", "GET", "DELETE");
			}
		};
	}


	public static void main(String[] args) {
		SpringApplication.run(AfekatonTestsApplication.class, args);
	}


}

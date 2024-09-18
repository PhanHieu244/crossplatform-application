package vn.edu.hust.project.crossplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CrossPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrossPlatformApplication.class, args);
	}

}

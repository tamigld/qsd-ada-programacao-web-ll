package tech.ada.queroserdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@EnableFeignClients
public class QueroSerDevApplication {

	public static void main(String[] args) {
		SpringApplication.run(QueroSerDevApplication.class, args);
	}

}

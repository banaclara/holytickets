package br.com.holytickets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HolyticketsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HolyticketsApplication.class, args);
	}

}

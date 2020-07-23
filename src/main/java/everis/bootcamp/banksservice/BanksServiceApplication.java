package everis.bootcamp.banksservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BanksServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BanksServiceApplication.class, args);
	}

}

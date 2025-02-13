package t221124nqt.ecommerce.hair_shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class HairShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(HairShopApplication.class, args);
	}
}

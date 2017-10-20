package sk.flowy.suggestedproductsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SuggestedProductsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuggestedProductsServiceApplication.class, args);
	}
}
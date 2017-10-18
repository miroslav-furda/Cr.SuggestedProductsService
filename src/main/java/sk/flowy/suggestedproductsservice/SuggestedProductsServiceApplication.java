package sk.flowy.suggestedproductsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "sk.flowy"})
public class SuggestedProductsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuggestedProductsServiceApplication.class, args);
	}
}
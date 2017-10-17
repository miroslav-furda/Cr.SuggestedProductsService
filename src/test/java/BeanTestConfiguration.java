import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import sk.flowy.suggestedproductsservice.repository.ProductRepository;

import static org.mockito.Mockito.mock;

@Configuration
@EnableJpaRepositories(basePackageClasses = ProductRepository.class)
public class BeanTestConfiguration {

    @Bean
    public ProductRepository productRepository() {
        return mock(ProductRepository.class);
    }
}

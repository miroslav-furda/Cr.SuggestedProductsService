package sk.flowy.suggestedproductsservice;

import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import sk.flowy.suggestedproductsservice.repository.EanRepository;
import sk.flowy.suggestedproductsservice.services.EanService;
import sk.flowy.suggestedproductsservice.services.EanServiceImpl;

import static org.mockito.Mockito.mock;

@ContextConfiguration
public class TestConfiguration {

    @Bean
    public EanService eanService(){
        return new EanServiceImpl(eanRepository());
    }

    @Bean
    public EanRepository eanRepository(){
        return mock(EanRepository.class);
    }
}

package sk.flowy.suggestedproductsservice.repository;

import org.springframework.data.repository.CrudRepository;
import sk.flowy.suggestedproductsservice.model.Ean;

public interface EanRepository extends CrudRepository<Ean, Long>{

    Ean findByValue(String value);
}

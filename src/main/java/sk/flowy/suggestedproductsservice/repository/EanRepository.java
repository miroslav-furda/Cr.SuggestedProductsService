package sk.flowy.suggestedproductsservice.repository;

import org.springframework.data.repository.CrudRepository;
import sk.flowy.suggestedproductsservice.model.Ean;

import java.util.List;

public interface EanRepository extends CrudRepository<Ean, Long> {

    List<Ean> findByValue(String value);
}

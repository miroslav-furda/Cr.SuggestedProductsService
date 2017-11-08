package sk.codexa.cr.suggestedproductsservice.repository;

import org.springframework.data.repository.CrudRepository;
import sk.codexa.cr.suggestedproductsservice.model.Ean;

/**
 * Simple {@link CrudRepository} or communicating with database.
 */
public interface EanRepository extends CrudRepository<Ean, Long>{

    /**
     * FInds Ean object based on ean value.
     *
     * @param value ean String value
     * @return ean object.
     */
    Ean findByValue(String value);
}

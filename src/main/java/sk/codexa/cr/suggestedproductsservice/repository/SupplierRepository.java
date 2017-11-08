package sk.codexa.cr.suggestedproductsservice.repository;

import org.springframework.data.repository.CrudRepository;
import sk.codexa.cr.suggestedproductsservice.model.Supplier;

public interface SupplierRepository extends CrudRepository<Supplier, Long> {

    Supplier findByName(String name);
}

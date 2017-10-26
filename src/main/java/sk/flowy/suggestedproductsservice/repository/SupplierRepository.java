package sk.flowy.suggestedproductsservice.repository;

import org.springframework.data.repository.CrudRepository;
import sk.flowy.suggestedproductsservice.model.Supplier;

public interface SupplierRepository extends CrudRepository<Supplier, Long> {

    Supplier findByName(String name);
}

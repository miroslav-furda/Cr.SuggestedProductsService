package sk.flowy.suggestedproductsservice.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import sk.flowy.suggestedproductsservice.model.Product;

import java.util.List;

/**
 * Created by peterszatmary on 17/10/2017.
 */
public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query("select p from Product p")
    List<Product> readProducts(); //TODO maybe input
}
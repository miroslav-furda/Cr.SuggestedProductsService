package sk.flowy.suggestedproductsservice.service;

import sk.flowy.suggestedproductsservice.model.NewProduct;
import sk.flowy.suggestedproductsservice.model.Product;

/**
 * Service represent set data for product
 */
public interface ProductDataService {

    /**
     * Method represent set data for product and insert product into database
     *
     * @param newProduct product from endpoint
     * @return actual saved product in database
     */
    Product setDataForProductAndSaveIntoDatabase(NewProduct newProduct);
}

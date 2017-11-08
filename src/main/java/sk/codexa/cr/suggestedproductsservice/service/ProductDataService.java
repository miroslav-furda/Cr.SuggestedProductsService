package sk.codexa.cr.suggestedproductsservice.service;

import sk.codexa.cr.suggestedproductsservice.model.NewProduct;
import sk.codexa.cr.suggestedproductsservice.model.Product;

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

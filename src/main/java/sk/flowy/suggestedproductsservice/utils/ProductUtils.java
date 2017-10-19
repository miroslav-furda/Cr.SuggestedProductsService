package sk.flowy.suggestedproductsservice.utils;

import sk.flowy.suggestedproductsservice.model.FoundProduct;
import sk.flowy.suggestedproductsservice.model.Product;

/**
 * Util class that contains helper method for Products.
 */
public class ProductUtils {

    private ProductUtils() {
        // intentionally empty private constructor
    }

    public static FoundProduct convertProduct(Product product) {
        return new FoundProduct(product.getId(), product.getName(), product.getEans(), 0.0,
                0.0);
    }
}

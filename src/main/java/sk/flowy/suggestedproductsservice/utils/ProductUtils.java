package sk.flowy.suggestedproductsservice.utils;

import sk.flowy.suggestedproductsservice.model.ReceiptProduct;
import sk.flowy.suggestedproductsservice.model.Product;

/**
 * Util class that contains helper method for Products.
 */
public class ProductUtils {

    /**
     * Intentionally empty private constructor
     */
    private ProductUtils() {
    }

    public static ReceiptProduct convertProduct(Product product) {
        return new ReceiptProduct(product.getId(), product.getName(), product.getEans());
    }
}

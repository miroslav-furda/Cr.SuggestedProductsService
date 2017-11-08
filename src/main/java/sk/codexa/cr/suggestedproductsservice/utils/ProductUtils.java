package sk.codexa.cr.suggestedproductsservice.utils;

import sk.codexa.cr.suggestedproductsservice.model.ReceiptProduct;
import sk.codexa.cr.suggestedproductsservice.model.Product;

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

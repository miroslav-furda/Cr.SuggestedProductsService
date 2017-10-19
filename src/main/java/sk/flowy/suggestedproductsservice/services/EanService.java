package sk.flowy.suggestedproductsservice.services;

import sk.flowy.suggestedproductsservice.model.ReceiptProduct;

/**
 * Provides ean specific business logic.
 */
public interface EanService {

    /**
     * Gets client specific receipt product by provided ean.
     *
     * @param ean ean code
     * @return {@link ReceiptProduct}
     */
    ReceiptProduct getProductByEan(String ean);
}

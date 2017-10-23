package sk.flowy.suggestedproductsservice.service;

import sk.flowy.suggestedproductsservice.model.CallResponse;

/**
 * Serves for business logic regarding suppliers.
 */
public interface SupplierService {

    /**
     * Adds relationship between specified product id and supplier id.
     *
     * @param productId id of product.
     * @param supplierId id of supplier.
     * @return response from action, either error or success.
     */
    CallResponse addProductToSupplier(Long productId, Long supplierId);
}

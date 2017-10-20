package sk.flowy.suggestedproductsservice.services;

import sk.flowy.suggestedproductsservice.model.CallResponse;

public interface SupplierService {

    CallResponse addProductToSupplier(Long productId, Long supplierId);
}

package sk.flowy.suggestedproductsservice.service;

import sk.flowy.suggestedproductsservice.model.CallResponse;

public interface SupplierService {

    CallResponse addProductToSupplier(Long productId, Long supplierId);
}

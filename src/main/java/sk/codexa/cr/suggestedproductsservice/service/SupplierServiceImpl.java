package sk.codexa.cr.suggestedproductsservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.codexa.cr.suggestedproductsservice.model.CallResponse;
import sk.codexa.cr.suggestedproductsservice.model.Product;
import sk.codexa.cr.suggestedproductsservice.model.Supplier;
import sk.codexa.cr.suggestedproductsservice.repository.ProductRepository;
import sk.codexa.cr.suggestedproductsservice.repository.SupplierRepository;

/**
 * Default implementation of {@link SupplierService}
 */
@Service
public class SupplierServiceImpl implements SupplierService {

    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

    /**
     * Constructor.
     *
     * @param productRepository autowired product repository.
     * @param supplierRepository autowired supplier repository.
     */
    @Autowired
    public SupplierServiceImpl(ProductRepository productRepository, SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public CallResponse addProductToSupplier(Long productId, Long supplierId) {
        Product product = productRepository.findOne(productId);
        Supplier supplier = supplierRepository.findOne(supplierId);

        if (product == null || supplier == null) {
            return new CallResponse(null, "Wrong input!");
        }

        if (product.getSuppliers().contains(supplier)) {
            return new CallResponse(null, "Product is already connected to the supplier.");
        }

        supplier.getProducts().add(product);
        supplierRepository.save(supplier);

        return new CallResponse("Product is successfully connected to the supplier", null);
    }
}

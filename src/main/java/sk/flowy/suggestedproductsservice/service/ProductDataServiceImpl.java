package sk.flowy.suggestedproductsservice.service;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.flowy.suggestedproductsservice.model.Ean;
import sk.flowy.suggestedproductsservice.model.NewProduct;
import sk.flowy.suggestedproductsservice.model.Product;
import sk.flowy.suggestedproductsservice.model.Supplier;
import sk.flowy.suggestedproductsservice.repository.ProductRepository;
import sk.flowy.suggestedproductsservice.repository.SupplierRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Log4j
public class ProductDataServiceImpl implements ProductDataService {

    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

    @Autowired
    public ProductDataServiceImpl(ProductRepository productRepository, SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Product setDataForProductAndSaveIntoDatabase(NewProduct newProduct) {
        Product product = new Product();
        product.setActive(1);
        Ean ean;
        List<Ean> eans = new ArrayList<>();

        if (newProduct.getName() != null) {
            product.setName(newProduct.getName());
        }
        if (newProduct.getEan() != null) {
            for (String eanValue : newProduct.getEan()) {
                ean = new Ean();
                ean.setValue(eanValue);
                ean.setType("single");
                ean.setProduct(product);
                eans.add(ean);
            }
            product.setEans(eans);
        }


        Supplier supplier = supplierRepository.findByName(newProduct.getSupplier());

        if (supplier != null) {
            product.setSuppliers(Arrays.asList(supplier));
        }

        try {
            return productRepository.save(product);
        } catch (IllegalArgumentException e) {
            log.error("Product can't save in database", e);
            return null;
        }
    }
}

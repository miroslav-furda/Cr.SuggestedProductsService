package sk.flowy.suggestedproductsservice.service;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.flowy.suggestedproductsservice.model.Ean;
import sk.flowy.suggestedproductsservice.model.NewProduct;
import sk.flowy.suggestedproductsservice.model.Product;
import sk.flowy.suggestedproductsservice.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j
public class ProductDataServiceImpl implements ProductDataService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductDataServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product setDataForProductAndSaveIntoDatabase(NewProduct newProduct) {
        Product product = new Product();
        product.setName(newProduct.getName());
        product.setActive(1);

        Ean ean;
        List<Ean> eans = new ArrayList<>();
        for (String eanValue : newProduct.getEan()) {
            ean = new Ean();
            ean.setValue(eanValue);
            ean.setType("single");
            ean.setProduct(product);
            eans.add(ean);
        }
        product.setEans(eans);
        product = productRepository.save(product);
        log.info("Product was saved into database");
        return product;
    }
}

package sk.flowy.suggestedproductsservice.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.flowy.suggestedproductsservice.model.CreateNewProduct;
import sk.flowy.suggestedproductsservice.model.Ean;
import sk.flowy.suggestedproductsservice.model.Product;
import sk.flowy.suggestedproductsservice.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api")
@Log4j
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping(value = "/product", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> createNewProduct(@RequestBody CreateNewProduct newProduct) {
        Product product = saveProductIntoDatabase(newProduct);
        return new ResponseEntity<>(product, OK);
    }

    @Transactional
    Product saveProductIntoDatabase(CreateNewProduct newProduct) {
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

package sk.flowy.suggestedproductsservice.controllers;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.flowy.suggestedproductsservice.model.Contractor;
import sk.flowy.suggestedproductsservice.model.CreateNewProduct;
import sk.flowy.suggestedproductsservice.model.Ean;
import sk.flowy.suggestedproductsservice.model.Product;
import sk.flowy.suggestedproductsservice.repository.EanRepository;
import sk.flowy.suggestedproductsservice.repository.ProductRepository;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api")
@Log4j
public class ProductController {

    private ProductRepository productRepository;
    private EanRepository eanRepository;

    @Autowired
    public ProductController(ProductRepository productRepository, EanRepository eanRepository){
        this.productRepository = productRepository;
        this.eanRepository = eanRepository;
    }


    @RequestMapping(value = "/product", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> createNewProduct(@RequestBody CreateNewProduct newProduct) {

        Product product = new Product();
        product.setName(newProduct.getProductName());
        product.setActive(true);
        product.setEans(eanRepository.findByValue(newProduct.getEanNumber()));

        Product savedProduct = productRepository.save(product);
        log.info("Product was saved into database");
        return new ResponseEntity<>(savedProduct, OK);
    }
}

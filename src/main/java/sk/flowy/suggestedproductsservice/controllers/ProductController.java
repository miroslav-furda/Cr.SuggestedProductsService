package sk.flowy.suggestedproductsservice.controllers;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.flowy.suggestedproductsservice.model.Product;
import sk.flowy.suggestedproductsservice.repository.ProductRepository;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api")
@Log4j
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = "/product", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> createNewProduct(@RequestBody Product product) {
        productRepository.save(product);
        log.info("Product was saved into database");
        return new ResponseEntity<>(product, OK);
    }
}

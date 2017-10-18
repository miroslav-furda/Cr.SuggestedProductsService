package sk.flowy.nofindproduct.controllers;

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
@RequestMapping("/no-find-product")
@Log4j
public class NoFindProductClientController {

    private ProductRepository productRepository;

    @Autowired
    public NoFindProductClientController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping(value = "/new-product", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> createNewProduct(@RequestBody Product product) {
        productRepository.save(product);
        return new ResponseEntity<>(product, OK);
    }
}

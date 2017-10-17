package sk.flowy.suggestedproductsservice.controllers;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.flowy.suggestedproductsservice.model.Product;
import sk.flowy.suggestedproductsservice.repository.ProductRepository;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@Log4j
public class ClientController {

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = "/products", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> readProducts() {

        log.info("repository = " + productRepository);
        return new ResponseEntity<>(productRepository.readProducts(), HttpStatus.OK);
    }
}
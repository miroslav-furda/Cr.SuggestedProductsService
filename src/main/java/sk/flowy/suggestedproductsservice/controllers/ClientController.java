package sk.flowy.suggestedproductsservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.flowy.suggestedproductsservice.model.Product;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class ClientController {

    @RequestMapping(value = "/products", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<List<Product>>(Arrays.asList(new Product("PEPSI"), new Product("COCA-COLA")), OK);
    }
}
